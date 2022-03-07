#Dar de alta zonas de influencia

Feature: como administrador quiero dar de alta una zona de influencia para que los sicarios puedan trabajar ahi

  Background:
    Given una lista de zonas=[{"zona1", 4}]
    And una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000}]

  Scenario: Agregar exitosamente
    Given una nueva zona={"zona2", 3}
    When no hay una zona con ese nombre
    And el numero maximo de sicarios es mayor que 0
    And el numero maximo de sicarios es menor que 6
    And el numero maximo de sicarios es menor o igual que la cantidad de sicarios disponibles (4)
    Then se agrega la zona a la lista de zonas
    And la lista de zonas queda==[{"zona1", 1}, {"zona2", 3}]

  Scenario: La zona ya existe
    Given una nueva zona={"zona1", 4}
    When hay una zona con ese nombre
    Then no se agrega la zona a la lista de zonas
    And se informa del error
    And la lista de zonas queda=[{"zona1", 1}]

  Scenario: Numero de sicarios menor que 1
    Given una nueva zona={"zona2", 0}
    When no hay una zona con ese nombre
    And el numero maximo de sicarios es menor que 1
    Then no se agrega la zona a la lista de zonas
    And se informa del error
    And la listas de zonas queda=[{"zona1", 1}]

  Scenario: Numero de sicarios mayor que 5
    Given una nueva zona={"zona2", 6}
    When no hay una zona con ese nombre
    And el numero maximo de sicarios es mayor que 1
    And el numero maximo de sicarios es mayor que 5
    Then no se agrega la zona a la lista de zonas
    And se informa del error
    And la listas de zonas queda=[{"zona1", 1}]


  Scenario: Numero de sicarios mayor que sicarios disponibles
    Given una nueva zona={"zona2", 5}
    When no hay una zona con ese nombre
    And el numero maximo de sicarios es mayor que 1
    And el numero maximo de sicarios es menor que 5
    And el numero maximo de sicarios es mayor que la cantidad total de sicarios (4)
    Then no se agrega la zona a la lista de zonas
    And se informa del error
    And la listas de zonas queda=[{"zona1", 1}]

