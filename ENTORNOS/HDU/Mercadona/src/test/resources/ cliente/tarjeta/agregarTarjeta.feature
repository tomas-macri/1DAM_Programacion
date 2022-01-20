#Cliente Mercadona
Feature: Como cliente quiero poder agregar una tarjeta a la lista de mis tarjetas
  Background:
    Given un usuario clientes
    When el usuario ingresa un 5
    And el numero de una tarjeta

  Scenario: agregar exitosamente
    When el numero tiene un valor
    Then se agrega la tarjeta a la lista de tarjetas
    And se informa que el producto se agrego a la lista de tarjetas
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

