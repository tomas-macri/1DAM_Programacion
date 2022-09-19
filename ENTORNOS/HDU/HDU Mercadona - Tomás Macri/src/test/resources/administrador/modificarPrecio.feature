#Administrador Mercadona
Feature: Como administrador quiero poder modificar el precio de un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: modificar exitosamente
    When el nombre del producto a modificar="jamon"
    And el nuevo precio=66
    And el precio es un valor positivo
    And hay un producto con ese nombre en la lista de productos
    Then Modifica el precio de el producto encontrado y lo reemplaza por el valor ingresado
    And Se informa de que el producto se modifico exitosamente
    And La lista de productos queda [{nombre= jamon, precio= 66, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: no se encuentra el producto a modificar
    When el nombre del producto a modificar="tortilla"
    And el nuevo precio = 44
    And el precio es un valor positivo
    And no hay un producto con el mismo nombre que el ingresado
    Then No se modifica el producto
    And Se informa que no se encontro ningun producto para modificar
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: nombre no valido
    When el nombre del producto a modificar=""
    And el precio = 4
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: precio nuevo negativo
    When el nombre del producto a modificar="jamon"
    And el precio = -1
    And el precio no es un valor positivo (-1)
    And hay un producto con ese nombre en la lista de productos
    Then No se busca el producto
    And Se pide un precio valido para poder modificar el producto
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

