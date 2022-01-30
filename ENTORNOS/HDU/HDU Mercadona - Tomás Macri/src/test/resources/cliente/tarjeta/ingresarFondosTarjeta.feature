#Cliente Mercadona
Feature: Como cliente quiero poder ingresar fondos a una tarjeta de mi lista de tarjetas para poder pagar mis compras con esa tarjeta
  Background:
    Given un usuario cliente
    And una lista de tarjetas [{numero=43, fondos=4333}, {numero=44, fondos=4689}]

  Scenario: agregar exitosamente
    When el numero de la tarjeta = 44
    And fondos para agregar = 1000
    And el numero de la tarjeta es un numero positivo
    And hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero positivo
    Then se agregan los fondos a los fondos de la tarjeta a la lista de tarjetas
    And se informa que los fondos se cargaron a la tarjeta
    And se muestran la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=5333}, {numero=44, fondos=4689}, {numero=444, fondos=44}]


  Scenario: la tarjeta no se encuentra en la lista de tarjetas
    When el numero de la tarjeta = 455
    And fondos para agregar = 400
    And el numero de la tarjeta es un numero positivo
    And no hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero positivo
    Then No se agregan fondos
    And Se informa que la tarjeta no estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]


  Scenario: los fondos son negativos
    When el numero de la tarjeta = 43
    And fondos = -4444
    And el numero de la tarjeta es un numero positivo
    And hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero negativo
    Then No se agregan los fondos
    And Se informa que los fondos deben ser positivos para poder sumarse a la tarjeta
    And Se muestra la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]


  Scenario: numero de tarjeta no valido
    When el numero de la tarjeta = -112
    And fondos = 456
    And el numero de la tarjeta es un numero negativo
    Then No se agregan los fondos
    And Se pide un numero valido poder agregar la tarjeta
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]




