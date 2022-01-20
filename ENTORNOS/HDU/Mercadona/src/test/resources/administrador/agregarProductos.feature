#Administrador Mercadona
Feature: Como administrador quiero poder agregar un producto a la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And el usuario ingresa un 1
    And un producto con un nombre, precio y stock

  Scenario: agregar exitosamente
    When el nombre tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock es mayor o igual que 0
    And no hay un producto con el mimso nombre en la lista de productos
    Then Agrega el producto a la lista de productos disponibles
    And Se informa de que el producto se agrego exitosamente

  Scenario: stock no valido
    When el nombre tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock tiene un valor menor que 0
    Then No se agrega el producto
    And Se pide un nuevo valor para el stock

  Scenario: nombre no valido
    When el nombre no tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock tiene un valor mayor o igual que 0
    Then No se agrega el producto
    And Se pide un nombre valido para el producto

    Scenario: precio no valido
      When el nombre no tiene un valor
      And el precio no tiene un valor o el valor del precio es no es mayor que 0
      And el stock tiene un valor mayor o igual que 0
      Then No se agrega el producto
      And Se pide un precio valido para el producto


