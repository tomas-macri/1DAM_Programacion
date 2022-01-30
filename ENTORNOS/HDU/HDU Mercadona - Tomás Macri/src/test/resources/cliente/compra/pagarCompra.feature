#Cliente Mercadona
Feature: Como cliente quiero poder pagar una vez realizada mi compra para poder terminar la compra

  Background:
    Given un usuario cliente
    And una lista de compras
    And un numero de tarjeta

    # Casos exitosos
  Scenario: pagar exitosamente
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta tiene los fondos necesarios para poder pagar
    Then se descuenta el importe de la compra de la tarjeta
    And se informa que la compra se efectuo exitosamente
    And se guarda la compra en la lista de compras

  Scenario: cargar fondos en la tarjeta y pagar en el momento
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta no tiene los fondos necesarios para poder pagar
    And se le informa al cliente que la tarjeta no tiene fondos suficientes
    And el usario si quiere cargar fondos en la tarjeta
    Then ingresa un monto minimo para poder continuar con la compra
    And se descuenta el importe de la compra de la tarjeta
    And se informa que la compra se efectuo exitosamente
    And se guarda la compra en la lista de compras

  Scenario: la tarjeta no tiene fondos y el cliente elimina productos para poder pagar
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta no tiene los fondos necesarios para poder pagar
    And se le informa al cliente que la tarjeta no tiene fondos suficientes
    And el usario no quiere cargar fondos en la tarjeta
    Then el usuario elimina los productos necesarios para poder continuar con la compra
    And se descuenta el importe de la compra de la tarjeta
    And se informa que la compra se efectuo exitosamente
    And se guarda la compra en la lista de compras


    #este escenario es la base de lo que sucedera si al momento de pagar el usuario no cuenta con los fondos suficientes
  Scenario: la tarjeta no tiene fondos
    When la lista tiene al menos un producto
    And el usuario tiene una tarjeta con el numero de tarjeta ingresado
    And la tarjeta no tiene los fondos necesarios para poder pagar
    Then se le informa al cliente que la tarjeta no tiene fondos suficientes
    And se pregunta al usario si quiere cargar fondos en la tarjeta


  Scenario: No hay productos en la lista de compras
    When la lista de compras no tiene productos = []
    Then ínformarle al cliente que su lista esta vacia
    And enviarlo de vuelta a la pagina de compras

  Scenario: la tarjeta no esta agregada
    When el numero tiene un valor
    And no hay una tarjeta con ese numero en la lista de tarjetas
    Then Se vuelve a la pagina de la compra
    And Se informa que la tarjeta no estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles

  Scenario: numero de tarjetano valido
    When el numero de la tarjeta no tiene un valor o tiene un valor negativo
    Then Se vuelve a la pagina de la compra
    And Se pide un numero valido poder agregar la tarjeta






