#Dar de alta sicarios

Feature: como administrador quiero dar de alta un sicario para que pueda empezar a trabajar

  Background:
    Given una lista de zonas=[{"zona1", 5},{"zona2", 3}]
    And una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]

  Scenario: Agregar exitosamente
    Given un nuevo sicario={"sic6", "zona2", 5, 6000}
    When no hay un sicario con ese nombre
    And la zona del sicario no alcanzo el numero maximo de sicarios
    And su habilidad es mayor que 0
    And su habilidad es menor que 6
    Then se agrega el sicario a la lista de sicarios
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000},{"sic6", "zona2", 5, 6000}]


  Scenario: ya hay un sicario con ese nombre
    Given un nuevo sicario={"sic1", "zona2", 5, 6000}
    When hay un sicario con ese nombre
    And la zona del sicario no alcanzo el numero maximo de sicarios
    And su habilidad es mayor que 0
    And su habilidad es menor que 6
    Then no se agrega el sicario a la lista
    And se informa del error
    Then no se agrega el sicario a la lista
    And se informa del error
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]

  Scenario: la zona del nuevo sicario ya tiene el numero maximo de sicarios
    Given un nuevo sicario={"sic6", "zona1", 5, 6000}
    When no hay un sicario con ese nombre
    And la zona del sicario alcanzo el numero maximo de sicarios
    And su habilidad es mayor que 0
    And su habilidad es menor que 6
    Then no se agrega el sicario a la lista
    And se informa del error
    Then no se agrega el sicario a la lista
    And se informa del error
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]

  Scenario: habilidad del sicario menor que 1
    Given un nuevo sicario={"sic6", "zona2", 0, 6000}
    When no hay un sicario con ese nombre
    And la zona del sicario no alcanzo el numero maximo de sicarios
    And su habilidad es menor o igual que 0
    And su habilidad es menor que 6
    Then no se agrega el sicario a la lista
    And se informa del error
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]


  Scenario: habilidad del sicario mayor que 5
    Given un nuevo sicario={"sic6", "zona2", 66, 6000}
    When no hay un sicario con ese nombre
    And la zona del sicario no alcanzo el numero maximo de sicarios
    And su habilidad es mayor que 0
    And su habilidad es mayor que 5
    Then no se agrega el sicario a la lista
    And se informa del error
    And la lista de sicarios queda=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]

