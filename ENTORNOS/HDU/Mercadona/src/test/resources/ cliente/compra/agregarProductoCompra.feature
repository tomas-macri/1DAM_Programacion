#Cliente Mercadona
Feature: Como cliente quiero poder agregar un producto de la lista de la compra
  Background:
    Given un usuario cliente
    And el usuario ingresa un 1
    And el nombre de un producto

  Scenario: agregar exitosamente
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de productos
    And el stock del producto es mayor que 0
    Then se agrega el producto a la lista de la compra
    And se resta 1 del stock del producto
    And se informa que el producto se agrego exitosamente a la lista de la compra

  Scenario: el producto existe pero no hay stock
    When el nombre tiene un valor
    And hay un producto con ese nombre en la lista de productos
    And el stock del producto es 0
    Then No se agrega el producto
    And Se informa que el producto deseado no tiene stock actualmente

  Scenario: el producto no existe
    When el nombre tiene un valor
    And no hay un producto con ese nombre en la lista de productos
    Then No se agrega nada a la lista
    And Se informa que el producto ingresado no existe

  Scenario: nombre no valido
    When el nombre no tiene un valor
    Then No se busca el producto
    And Se pide un nombre valido para poder buscar en la lista de la compra



