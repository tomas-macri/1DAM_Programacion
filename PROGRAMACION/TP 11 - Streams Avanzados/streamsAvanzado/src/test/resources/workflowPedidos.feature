# Created by oscar at 4/23/2020
Feature: Workflow de pedidos
  comprobacion del paso de los pedidos por los diferentes estados

  Scenario: cobrar pedidos con saldo
    Given un cliente con saldo
    And un pedido de ese cliente
    When cobrar el pedido
    Then el pedido pasa a estado confirmado

  Scenario: cobrar pedidos sin saldo
    Given un cliente sin saldo sufiente para el pedido
    And un pedido de ese cliente
    When cobrar el pedido
    Then el pedido pasa a estado rechazado

  Scenario: distribuir pedidos confirmados
    Given un pedido confirmado
    When distruibir pedidos
    Then el pedido pasa a estado en distribucion

  Scenario: entregar pedidos en distribucion
    Given un pedido en distribucion
    When entregar pedidos
    Then el pedido pasa a estado entregado

  Scenario: entregar pedidos rechazados
    Given un pedido rechazado
    When entregar pedidos
    Then el pedido sigue en estado rechazado
