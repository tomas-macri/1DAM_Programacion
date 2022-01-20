#Cliente Mercadona
Feature: Como cliente quiero poder pagar una vez realizada mi compra
  Background:
    Given un usuario cliente
    And una lista de compras
    And un numero de tarjeta
    And el usuario ingresa un 3


  Scenario: pagar exitosamente
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta tiene los fondos necesarios para poder pagar
    Then se descuenta el importe de la compra de la tarjeta
    And se informa que la compra se efectuo exitosamente


  Scenario: la tarjeta no esta agregada
    When el numero tiene un valor
    And no hay una tarjeta con ese numero en la lista de tarjetas
    Then Se vuelve a la pagina de la compra
    And Se informa que la tarjeta no estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles

  Scenario: numero no valido
    When el numero no tiene un valor o tiene un valor negativo
    Then Se vuelve a la pagina de la compra
    And Se pide un numero valido poder agregar la tarjeta


  Scenario: la tarjeta no tiene fondos
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta no tiene los fondos necesarios para poder pagar
    Then se le da la posibilidad al usario de cargar fondos en la tarjeta
    And ..................................

