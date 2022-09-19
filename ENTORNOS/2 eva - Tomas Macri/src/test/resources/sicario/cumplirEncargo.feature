#Cumplir encargos
Feature: Como sicario quiero asignar el estado de mis encargos a cumplidos para indicar que el trabajo esta hecho

  Background:
    Given un sicario={"sic4", "zona1", 4, 4000}
    Given una lista de encargos del sicario=[{E001, 95KB, 1000, 1, "validado", "sic4"},{E002, 50KB, 2000, 2, "cumplido", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic4"}]


  Scenario: Cumplir correctamente
    Given un id de encargo=E001
    When hay un encargo con ese id
    And el estado de ese encargo="validado"
    Then el estado del encargo cambia a "cumplido"
    And la lista de encargos del sicario=[{E001, 95KB, 1000, 1, "cumplido", "sic4"},{E002, 50KB, 2000, 2, "cumplido", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic4"}]


  Scenario: No existe un encargo con ese id
    Given un id de encargo=E010
    When no hay un encargo con ese id
    Then no se modifica la lista de encargos
    And se informa del error
    And la lista de encargos del sicario=[{E001, 95KB, 1000, 1, "validado", "sic4"},{E002, 50KB, 2000, 2, "cumplido", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic4"}]


  Scenario: El encargo no esta en el estado validado
    Given un id de encargo=E002
    When hay un encargo con ese id
    And el estado de ese encargo no es = "validado"
    Then no se modifica la lista de encargos
    And se informa del error
    Given una lista de encargos del sicario=[{E001, 95KB, 1000, 1, "validado", "sic4"},{E002, 50KB, 2000, 2, "cumplido", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic4"}]


