#Pagar encargos
Feature: Como administrador quiero pagar los encargos que cumplidos por los sicarios para no ser yo su siguiente encargo

  Background:
    Given una lista de encargos=[{E001, 95KB, 1000, 1, "cumplido", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]
    And una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]


  Scenario: Pagar correctamente
    Given un id de encargo=E001
    When hay un encargo con ese id
    And el estado de ese encargo="cumplido"
    Then se le suma el dinero del encargo al sicario que lo realizo
    And el estado del encargo cambia a "pagado"
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "pagado", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 5000},{"sic5", "zona1", 5, 5000}]


  Scenario: No existe un encargo con ese id
    Given un id de encargo=E010
    When no hay un encargo con ese id
    Then no se modifica la lista de encargos
    And se informa del error
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "cumplido", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

  Scenario: El encargo no esta en la fase de validacion
    Given un id de encargo=E002
    When hay un encargo con ese id
    And el estado de ese encargo no es = "cumplido"
    Then no se modifica la lista de encargos
    And se informa del error
    And la lista de encargos queda=[{E001, 95KB, 1000, 1, "cumplido", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

