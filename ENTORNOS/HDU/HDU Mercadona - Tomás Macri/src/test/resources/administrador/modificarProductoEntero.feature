#Administrador Mercadona
Feature: Como administrador quiero poder modificar un producto entero de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: modificar exitosamente
    When el nombre del producto a modificar = "jamon"
    And el nuevo nombre = "tortilla"
    And el nuevo precio = 5
    And el nuevo stock = 44
    And hay un producto con ese nombre en la lista de productos
    And el precio es un numero positivo
    And el stock es un numero positivo
    Then Modifica los atirbutos del producto por los ingresados
    And Se informa de que el producto se modifico exitosamente
    And La lista de productos queda [{nombre= tortilla, precio= 5, stock= 44}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: no se encuentra el producto a modificar
    When el nombre de producto a modificar = "tortilla"
    And el nuevo nombre = "paella"
    And el nuevo precio = 45
    And el nuevo stock = 444
    And no hay un producto con el mismo nombre que el ingresado
    Then No se modifica el producto
    And Se informa que no se encontro ningun producto para modificar
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

    #Errores en el nuevo nombre
  Scenario: se quiere modificar por un producto existente
    When el nombre de producto a modificar = "jamon"
    And el nuevo nombre = "queso"
    And el nuevo precio = 45
    And el nuevo stock = 444
    And hay un producto con ese nombre en la lista de productos
    And hay un producto con el nuevo nombre en la lista de productos
    And el precio es un numero positivo
    And el stock es un numero positivo
    Then No modifica el nombre de el producto encontrado y lo reemplaza por el nombre nuevo ingresado
    And Se informa de que el producto no se puede modificar por un nombre de otro producto de la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  Scenario: nombre no valido
    When el nombre de producto a modificar = ""
    And el nuevo nombre = "tortilla"
    And el nuevo precio = 45
    And el nuevo stock = 444
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

  Scenario: nuevo nombre no valido
    When el nombre de producto a modificar = "jamon"
    And el nuevo nombre = ""
    And el nuevo precio = 45
    And el nuevo stock = 444
    Then No se busca el producto
    And Se pide un nombre valido para poder reemplazar al producto en la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


    #Error en el nuevo precio

  Scenario: precio nuevo negativo
    When el nombre del producto a modificar="jamon"
    And el nuevo nombre ="tortilla"
    And el precio = -1
    And el stock = 55
    And el stock es un numero positivo
    And el precio no es un valor positivo (-1)
    And hay un producto con ese nombre en la lista de productos
    Then No se busca el producto
    And Se pide un precio valido para poder modificar el producto
    And una lista de productos [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]


  #Error en el stock

  Scenario: nuevo stock negativo
    When el nombre del producto a modificar="jamon"
    And el nuevo nombre ="tortilla"
    And el nuevo precio = 55
    And el nuevo stock = -1
    When el nombre tiene un valor
    And el precio es un numero positivo
    And el stock no es un numero positivo (-1)
    Then No se busca el producto
    And Se pide un stock valido para poder modificar la lista de productos
    And La lista de productos queda [{nombre= jamon, precio= 15, stock= 10}, {nombre= queso, precio= 20, stock= 11}]

