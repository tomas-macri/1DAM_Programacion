#Validar encargos
Feature: Como administrador quiero autorizar los encargos que se asignaron los sicarios para que puedan ir a cumplir con su deber

  Background:
    Given una lista de encargos=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]
    And una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]



  Scenario: Asignar correctamente
    Given un id de encargo=E001
    When hay un encargo con ese id
    And el estado de ese encargo="esperando-confirmacion"
    And se ingresa una V
    Then el estado del encargo cambia a "validado"
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "validado", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]


  Scenario: No asignar correctamente
    Given un id de encargo=E001
    When hay un encargo con ese id
    And el estado de ese encargo="esperando-confirmacion"
    And se ingresa una N
    Then el estado del encargo cambia a "entrada"
    And se borra el nombre del sicario del encargo
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

  Scenario: No existe un encargo con ese id
    Given un id de encargo=E010
    When no hay un encargo con ese id
    Then no se modifica la lista de encargos
    And se informa del error
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

  Scenario: El encargo no esta en la fase de validacion
    Given un id de encargo=E002
    When hay un encargo con ese id
    And el estado de ese encargo no es = "esperando-confirmacion"
    Then no se modifica la lista de encargos
    And se informa del error
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

