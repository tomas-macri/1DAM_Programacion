#Administrador Mercadona
Feature: Como administrador quiero poder modificar el nombre de un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: modificar exitosamente
    When el nombre de producto a modificar = "jamon"
    And el nuevo nombre = "tortilla"
    And hay un producto con ese nombre en la lista de productos
    And no hay un producto con el nuevo nombre en la lista de productos
    Then Modifica el nombre de el producto encontrado y lo reemplaza por el nombre nuevo ingresado
    And Se informa de que el producto se modifico exitosamente
    And La lista de productos queda [{nombre= tortilla, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: se quiere modificar por un producto existente
    When el nombre de producto a modificar = "jamon"
    And el nuevo nombre = "queso"
    And hay un producto con ese nombre en la lista de productos
    And hay un producto con el nuevo nombre en la lista de productos
    Then No modifica el nombre de el producto encontrado y lo reemplaza por el nombre nuevo ingresado
    And Se informa de que el producto no se puede modificar por un nombre de otro producto de la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: no se encuentra el producto a modificar
    When el nombre de producto a modificar = "tortilla"
    And el nuevo nombre = "paella"
    And no hay un producto con el mismo nombre que el ingresado
    Then No se modifica el producto
    And Se informa que no se encontro ningun producto para modificar
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: nombre no valido
    When el nombre de producto a modificar = ""
    And el nuevo nombre = "tortilla"
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: nuevo nombre no valido
    When el nombre de producto a modificar = "jamon"
    And el nuevo nombre = ""
    Then No se busca el producto
    And Se pide un nombre valido para poder reemplazar al producto en la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


