# Created by oscar at 4/23/2020
Feature: Admin Clientes

  El Crud de clientes se incluye aqui.

  Scenario: Add un cliente
    Given un cliente que no existe
    When add el cliente al sistema
    Then el cliente esta en el sistema

  Scenario: Add un cliente con un email que ya existe
    Given un cliente con un email que existe
    When add el cliente al sistema
    Then el cliente no se añade al sistema


  Scenario: Borrar un cliente que existe
    Given un cliente del sistema
    When borramos el cliente
    Then el cliente deja de estar en el sistema

  Scenario: Pedir la lista de todos los clientes
    Given un cliente del sistema
    When pedimos la lista de clientes
    Then el cliente tiene que estar en esa lista

  Scenario: Pedir la lista de todos los clientes y comprobar un cliente borrado
    Given un cliente borrado del sistema
    When pedimos la lista de clientes
    Then el cliente no tiene que estar en esa lista


  Scenario: Pedir cliente que existe por email
    Given un cliente con email añadido al sistema
    When pido un cliente por email
    Then el cliente devuelto es el mismo

  Scenario: Pedir cliente que no existe por email
    Given un cliente con email que no existe en el sistema
    When pido un cliente por email
    Then el cliente devuelto es null
