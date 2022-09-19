#Para los encargos, ademas de la foto, dinero, habilidad y estado
# tambien se guardará el nombre del sicario que lo tenga asignado
# o "" cuando no esté asignado. Esto es para poder saber si al dar
# de baja un sicario este tenía encargos que hay que renombrar o no.
# Tambien se le asignara un id al encargo para que el administrador
# pueda poder validar el encargo a la hora de que un sicario se lo asigne.
  
Feature: Como administrador quiero dar de baja un sicario muerto o detenido para que no me figure más en mi base de datos

  Background:
    Given una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]
    And una lista de encargos=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]

  Scenario: baja exitosamente
    Given un nombre de sicario a eliminar="sic2"
    When hay un sicario con ese nombre en mi lista de sicarios
    Then quito a ese sicario de mi lista de sicarios
    And cambio el estado de todos los encargos asignados a ese sicario a "entrada"
    And cambio el el sicario asignado de todos los encargos asignados a ese sicario a ""
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "entrada", ""}]

  Scenario: sicario inexistente
    Given un nombre de sicario a eliminar="oscar"
    When no hay un sicario con ese nombre en mi lista de sicarios
    Then no modifico la lista de sicarios
    And no modifico la lista de encargos
    And informo que no se encontro un sicario con ese nombre
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]

