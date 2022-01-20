#Cliente Mercadona
Feature: Como cliente quiero poder ingresar fondos a una tarjeta de mi lista de tarjetas
  Background:
    Given un usuario cliente
    And el usuario ingresa un 6
    And el numero de una tarjeta
    And el importe a ingresar

  Scenario: ingresar exitosamente
    When el numero tiene un valor
    And hay una tarjeta con ese numero en la lista de las tarjetas del usuario
    And el importe a ingresar es mayor que 0
    Then se le suma el importe a los fondos de la tarjeta
    And se informa que la tarjeta recibio un ingreso de dinero

  Scenario: la tarjeta no esta agregada
    When el numero tiene un valor
    And no hay una tarjeta con ese numero en la lista de tarjetas
    Then No se realiza el ingreso
    And Se informa que la tarjeta no esta entre las tarjetas del cliente
    And Se muestra la lista con las tarjetas disponibles

  Scenario: numero no valido
    When el numero no tiene un valor o tiene un valor negativo
    Then No se busca la tarjeta
    And Se pide un numero valido poder realizar el ingreso

  Scenario: importe no valido
    When el numero tiene un valor
    And hay una tarjeta con ese numero en la lista de las tarjetas del usuario
    And el importe a ingresar es menor que 0
    And Se pide un importe valido poder realizar el ingreso
