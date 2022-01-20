#Administrador Mercadona
Feature: Como administrador quiero poder eliminar un producto de la lista de productos para actualizar la lista
  Background:
    Given un usuario administrador
    And el usuario ingresa un 2
    And el nombre de un producto

  Scenario: eliminar exitosamente
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de productos
    Then se elimina el producto de la lista de productos
    And Se informa de que el producto se elimino exitosamente

  Scenario: no se encuentra el producto a eliminar
    When el nombre tiene un valor
    And no hay un producto con el mismo nombre que el ingresado
    Then No se elimina nada
    And Se informa que no se encontro ningun producto para eliminar

  Scenario: nombre no valido
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de productos

