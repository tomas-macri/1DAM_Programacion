# Created by oscar at 4/23/2020
Feature: Admin Cuentas de Clientes
  es la administración de las cuentas de los clientes.

  Scenario: añadir cuenta a cliente con tarjeta que no existe en el sistema
    Given un cliente del sistema
    And una cuenta con tarjeta que no existe en el sistema
    When añado la cuenta al cliente
    Then el cliente tiene la cuenta añadida

  Scenario: añadir cuenta a cliente con tarjeta que existe en el sistema
    Given un cliente del sistema
    And una cuenta con tarjeta que no existe en el sistema
    When añado la cuenta al cliente
    Then da error de que la tarjeta ya existe en el sistema

  Scenario: añadir cuenta a cliente que no existe en el sistema
    Given un cliente fuera sistema
    And una cuenta con tarjeta que no existe en el sistema
    When añado la cuenta al cliente
    Then dara error de cliente no existe

  Scenario: añadir saldo a una cuenta a cliente que no existe en el sistema
    Given un cliente fuera sistema
    And una cuenta con tarjeta que no existe en el sistema
    When añado la cuenta al cliente
    Then dara error de cliente no existe

  Scenario: añadir saldo a una cuenta a cliente que existe en el sistema
    Given un cliente del sistema
    And una cuenta de ese cliente
    When añado saldo a la cuenta
    Then el saldo de la cuenta se incrementa
