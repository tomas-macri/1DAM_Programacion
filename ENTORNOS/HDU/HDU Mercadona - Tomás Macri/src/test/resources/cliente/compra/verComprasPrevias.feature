#Cliente Mercadona
Feature: Como cliente quiero poder ver mis compras previas para poder informarme de los productos que compre
  Background:
    Given un usuario cliente

  Scenario: ver compras exitosamente
    Given una lista de compras previas = [{compra1, { {nombre="queso", precio=44}, {nombre=jamon, precio=55} } }, { {compra2, { {nombre="tortilla", precio=44}, {nombre=jamon, precio=55} } } ]
    When el cliente realizo compras previamente
    Then se muestran la lista de compras previas

  Scenario: el usuario no tiene compras realizadas previamente
    Given una lista de compras previsas = []
    When el cliente no realizo compras previamente
    Then se informa al cliente que no tiene compras realizadas
