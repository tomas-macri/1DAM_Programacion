#Cliente Mercadona
Feature: Como cliente quiero poder eliminar un producto de la lista de la compra para poder dejarlo
  Background:
    Given un usuario cliente
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}, {nombre=tortilla, precio=22, stock=0}]
    And una lista de compra [{nombre= queso, precio= 20}]

  Scenario: eliminar exitosamente
    When el nombre del producto a eliminar = "queso"
    And hay un producto con ese nombre en la lista de la compra
    Then se elimina el producto de la lista de la compra
    And se agrega 1 al stock del producto
    And se informa que el producto se elimino exitosamente de la lista de la compra
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 12}, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[]

  Scenario: el producto no esta en la lista de la compra
    When el nombre del producto a eliminar="jamon"
    And no hay un producto con ese nombre en la lista de la compra
    Then No se elimina nada
    And Se informa que el producto no esta en la lista de la compra
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= queso, precio= 20}]

  Scenario: nombre no valido
    When el nombre del producto a eliminar=""
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de la compra
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}, {nombre=tortilla, precio=22, stock=0}]
    And La lista de la compra queda[{nombre= queso, precio= 20}]



