#Cliente Mercadona
Feature: Como cliente quiero poder agregar una tarjeta a la lista de mis tarjetas para poder usarla en futuras compras
  Background:
    Given un usuario cliente
    And una lista de tarjetas [{numero=43, fondos=4333}, {numero=44, fondos=4689}]

  Scenario: agregar exitosamente
    When el numero de la tarjeta = 444
    And fondos = 44
    And el numero de la tarjeta es un numero positivo
    And no hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero positivo
    Then se agrega la tarjeta a la lista de tarjetas
    And se informa que la tarjeta se agrego a la lista de tarjetas
    And se muestran la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}, {numero=444, fondos=44}]


  Scenario: la tarjeta ya estaba agregada
    When el numero de la tarjeta = 43
    And fondos = 4444
    And el numero de la tarjeta es un numero positivo
    And hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero positivo
    Then No se agrega la tarjeta
    And Se informa que la tarjeta ya estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]


  Scenario: los fondos son negativos
    When el numero de la tarjeta = 48
    And fondos = -4444
    And el numero de la tarjeta es un numero positivo
    And no hay una tarjeta con ese numero en la lista de tarjetas
    And fondos es un numero negativo
    Then No se agrega la tarjeta
    And Se informa que los fondos deben ser positivos para agregar la tarjeta
    And Se muestra la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]


  Scenario: numero de tarjeta no valido
    When el numero de la tarjeta = -112
    And fondos = 456
    And el numero de la tarjeta es un numero negativo
    Then No se agrega la tarjeta
    And Se pide un numero valido poder agregar la tarjeta
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]




