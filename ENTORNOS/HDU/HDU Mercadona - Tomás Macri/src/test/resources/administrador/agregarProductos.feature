#Administrador Mercadona
Feature: Como administrador quiero poder agregar un producto a la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: agregar exitosamente
    Given un producto {nombre= tortilla, precio= 33, stock= 5}
    When el nombre tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock es mayor o igual que 0
    And no hay un producto con el mismo nombre en la lista de productos
    Then Agrega el producto a la lista de productos disponibles
    And Se informa de que el producto se agrego exitosamente
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}, {nombre= tortilla, precio= 33, stock= 5}]


  Scenario: ya hay un producto con ese nombre
    Given un producto {nombre= jamon, precio= 33, stock= 5}
    When el nombre tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock es mayor o igual que 0
    And hay un producto con el mismo nombre en la lista de productos
    Then No agrega el producto a la lista de productos disponibles
    And Se informa de que ya hay un producto con ese nombre
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]



  Scenario: stock no valido
    Given un producto {nombre= tortilla, precio= 33, stock= -1}
    When el nombre tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock tiene un valor menor que 0 (-1)
    Then No se agrega el producto
    And Se pide un nuevo valor para el stock
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: nombre no valido
    Given un producto {nombre= , precio= 45, stock= 5}
    When el nombre no tiene un valor
    And el precio tiene un valor
    And el valor del precio es mayor que 0
    And el stock tiene un valor mayor o igual que 0
    Then No se agrega el producto
    And Se pide un nombre valido para el producto
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

    Scenario: precio no valido
      When el nombre no tiene un valor
      And el precio no tiene un valor o el valor del precio es no es mayor que 0
      And el stock tiene un valor mayor o igual que 0
      Then No se agrega el producto
      And Se pide un precio valido para el producto
      And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


