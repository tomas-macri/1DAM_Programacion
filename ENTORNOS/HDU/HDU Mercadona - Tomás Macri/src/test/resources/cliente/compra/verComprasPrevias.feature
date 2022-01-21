#Cliente Mercadona
Feature: Como cliente quiero poder ver mis compras previas para poder informarme de los productos que compre
  Background:
    Given un usuario cliente
    And el usuario ingresa un 7


  Scenario: ver compras exitosamente
    When el cliente realizo compras previamente
    Then se muestran todas las compras que realizo

  Scenario: el usuario no tiene compras realizadas previamente
    When el cliente no realizo compras previamente
    Then se informa al cliente que no tiene compras realizadas
