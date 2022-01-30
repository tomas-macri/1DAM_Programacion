#Administrador Mercadona
Feature: Como administrador quiero poder modificar el stock de un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: modificar exitosamente
    When el nombre del producto a modificar="jamon"
    And el nuevo stock = 8
    And hay un producto con ese nombre en la lista de productos
    And el stock no es un numero positivo
    Then Modifica el stock de el producto encontrado y lo reemplaza por el valor ingresado
    And Se informa de que el producto se modifico exitosamente
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 8}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: no se encuentra el producto a modificar
    When el nombre del producto a modificar="tortilla"
    And el nuevo stock = 5
    And no hay un producto con el mismo nombre que el ingresado
    Then No se modifica el producto
    And Se informa que no se encontro ningun producto para modificar
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: nombre no valido
    When el nombre del producto a modificar=""
    And el nuevo stock = 8
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: nuevo stock negativo
    When el nombre del producto a modificar="jamon"
    And el nuevo stock = -1
    When el nombre tiene un valor
    And el stock no es un numero positivo (-1)
    Then No se busca el producto
    And Se pide un stock valido para poder modificar la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


