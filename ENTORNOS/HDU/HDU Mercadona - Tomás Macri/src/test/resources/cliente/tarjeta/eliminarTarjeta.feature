#Cliente Mercadona
Feature: Como cliente quiero poder agregar una tarjeta a la lista de mis tarjetas para no usarla en futuras compras
  Background:
    Given un usuario cliente
    And una lista de tarjetas [{numero=43, fondos=4333}, {numero=44, fondos=4689}]

  Scenario: eliminar exitosamente
    When el numero de la tarjeta a eliminar = 44
    And el numero de la tarjeta es un numero positivo
    And hay una tarjeta con ese numero en la lista de tarjetas
    Then se elimina la tarjeta a la lista de tarjetas
    And se informa que la tarjeta se elimino a la lista de tarjetas
    And se muestran la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}]


  Scenario: la tarjeta no estaba en la lista
    When el numero de la tarjeta = 436
    And el numero de la tarjeta es un numero positivo
    And no hay una tarjeta con ese numero en la lista de tarjetas
    Then No se elimina la tarjeta
    And Se informa que la tarjeta no estaba agregada previamente
    And Se muestra la lista con las tarjetas disponibles
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]


  Scenario: numero de tarjeta no valido
    When el numero de la tarjeta = -112
    And el numero de la tarjeta es un numero negativo
    Then No se elimina la tarjeta
    And Se pide un numero valido poder eliminar la tarjeta
    And la lista de tarjetas queda [{numero=43, fondos=4333}, {numero=44, fondos=4689}]