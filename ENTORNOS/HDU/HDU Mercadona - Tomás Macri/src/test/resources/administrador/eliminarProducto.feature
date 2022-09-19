#Administrador Mercadona
Feature: Como administrador quiero poder eliminar un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: eliminar exitosamente
    When el nombre ingresado es "jamon"
    And hay un producto con ese nombre en la lista de productos
    Then se elimina el producto de la lista de productos
    And Se informa de que el producto se elimino exitosamente
    And La lista de productos queda [{nombre= queso, precio= 20, stock= 11}]

  Scenario: no se encuentra el producto a eliminar
    When se ingresa "tortilla"
    And no hay un producto con el mismo nombre que el ingresado
    Then No se elimina nada
    And Se informa que no se encontro ningun producto para eliminar
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: nombre no valido
    When se ingresa ""
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productosç
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


