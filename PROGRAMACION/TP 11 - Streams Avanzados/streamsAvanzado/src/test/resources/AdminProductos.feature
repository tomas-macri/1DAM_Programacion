# Created by oscar at 4/23/2020
Feature: AdminProductos
  Creacion de productos y su stock

  Scenario: Add un producto
    Given un producto
    When añado el producto al sistema
    Then el producto se añade


  Scenario: ver lista de productos
    Given un producto añadido al sistema
    When veo la lista de productos del sistema
    Then el producto este en esa lista

  Scenario: añadir stock a producto
    Given un producto añadido al sistema
    When añado stock al producto
    Then el stock producto aumenta