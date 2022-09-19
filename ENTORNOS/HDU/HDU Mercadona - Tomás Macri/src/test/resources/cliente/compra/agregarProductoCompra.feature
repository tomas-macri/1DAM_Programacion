#Cliente Mercadona
Feature: Como cliente quiero poder agregar un producto de la lista de la compra para poder llevarmelo
  Background:
    Given un usuario cliente
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}, {nombre=tortilla, precio=22, stock=0}]
    And una lista de compra [{nombre= queso, precio= 20}]


  Scenario: agregar exitosamente
    When se ingresa un nombre="jamon"
    And hay un producto con ese nombre en la lista de productos
    And el stock del producto es mayor que 0
    Then se agrega el producto a la lista de la compra
    And se resta 1 del stock del producto
    And se informa que el producto se agrego exitosamente a la lista de la compra
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 9}, {nombre= queso, precio= 20, stock= 11}, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= jamon, precio= 15}, {nombre= queso, precio= 20}]



  Scenario: el producto existe pero no hay stock
    When se ingresa un nombre="tortilla"
    And hay un producto con ese nombre en la lista de productos
    And el stock del producto es 0
    Then No se agrega el producto
    And Se informa que el producto deseado no tiene stock actualmente
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11},, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= queso, precio= 20}]


  Scenario: el producto no existe
    When se ingresa un nombre="paella"
    And no hay un producto con ese nombre en la lista de productos
    Then No se agrega nada a la lista
    And Se informa que el producto ingresado no existe
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11},, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= queso, precio= 20}]

  Scenario: nombre no valido
    When se ingresa un nombre = ""
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de la compra
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11},, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= queso, precio= 20}]



