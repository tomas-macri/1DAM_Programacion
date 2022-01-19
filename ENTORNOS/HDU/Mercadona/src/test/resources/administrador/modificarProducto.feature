#Administrador Mercadona
Feature: Como administrador quiero poder modificar un producto de la lista de productos
  Background:
    Given un usuario administrador
    When el usuario ingresa un 2
    And el nombre de un producto
    And ingresa un producto con un nombre, precio y stock validos

  Scenario: modificar exitosamente
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de productos
    Then Modifica los atirbutos del producto por los ingresados
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

