#Cliente Mercadona
Feature: Como cliente quiero poder eliminar un producto de la lista de la compra
  Background:
    Given un usuario cliente
    When el usuario ingresa un 2
    And el nombre de un producto

  Scenario: eliminar exitosamente
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de la compra
    Then se elimina el producto de la lista de la compra
    And se agrega 1 al stock del producto
    And se informa que el producto se elimino exitosamente de la lista de la compra

  Scenario: el producto existe pero no
    When el nombre tiene un valor
    And no hay un producto con ese nombre en la lista de la compra
    Then No se elimina nada
    And Se informa que el producto no esta en la lista de la compra

  Scenario: nombre no valido
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de la compra


