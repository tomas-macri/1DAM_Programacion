#Administrador Mercadona
Feature: Como administrador quiero poder modificar el nombre de un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And el usuario ingresa un 2
    #menu en el que elige entre agregar, modificar o eliminar

    And el usuario ingesa un 4
    #menu en el que elige entre modificar un producto entero, solo su stock, solo su precio o solo su nombre
    And el nombre de un producto
    And ingresa un nombre nuevo valido

  Scenario: modificar exitosamente
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de productos
    Then Modifica el nombre de el producto encontrado y lo reemplaza por el nombre nuevo ingresado
    And Se informa de que el producto se modifico exitosamente

  Scenario: no se encuentra el producto a modificar
    When el nombre tiene un valor
    And no hay un producto con el mismo nombre que el ingresado
    Then No se modifica el producto
    And Se informa que no se encontro ningun producto para modificar

  Scenario: nombre no valido
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos

