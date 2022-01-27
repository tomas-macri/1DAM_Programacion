#Cliente Mercadona
Feature: Como cliente quiero poder agregar una tarjeta a la lista de mis tarjetas para poder usarla en futuras compras
  Background:
    Given un usuario cliente
    And el usuario ingresa un 4
    And el numero de una tarjeta

  Scenario: agregar exitosamente
    When el numero tiene un valor
    And no hay una tarjeta con ese numero en la lista de tarjetas
    Then se agrega la tarjeta a la lista de tarjetas
    And se informa que la tarjeta se agrego a la lista de tarjetas
    And se muestran la lista con las tarjetas disponibles

  Scenario: la tarjeta ya estaba agregada
    When el numero tiene un valor
    And hay una tarjeta con ese numero en la lista de tarjetas
    Then No se agrega la tarjeta
    And Se informa que la tarjeta ya estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles

  Scenario: numero no valido
    When el numero no tiene un valor o tiene un valor negativo
    Then No se agrega la tarjeta
    And Se pide un numero valido poder agregar la tarjeta

