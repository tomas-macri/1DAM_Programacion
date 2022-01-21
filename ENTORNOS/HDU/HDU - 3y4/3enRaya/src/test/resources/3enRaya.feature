#3 en Raya
Feature: Como usuario quiero poner una ficha y comprobar si hay 3 en raya
  Background:
    Given es el turno del jugador que usa X

  Scenario: celda libre
  Description: las coordenadas del tablero inician en el punto (0, 0) y terminan en el punto (2, 2)
    Given un tablero de 3x3
    And un jugador que tiene que colocar una X
      # - - -
      # - - -
      # - - -
    When elige casilla (0, 0)
    And no hay ninguna ficha colocada en esa posicion
    Then Pone una X en la posicion seleccionada
      # X - -
      # - - -
      # - - -
    And como no hay 3 en raya, cambio de turno de jugador

  Scenario: celda ocupada
  Description: las coordenadas del tablero inician en el punto (0, 0) y terminan en el punto (2, 2)
    Given un tablero de 3x3 con una ficha colocada en la posicion (1, 1)
      # - - -
      # - X O
      # - - -
    When elijo casilla (1, 1)
    And hay ninguna ficha colocada en esa posicion
    Then Se pide otra ficha


    ###############Ingreso de fichas en posiciones no válidas

  Scenario: celda inexistente en el eje x hacia la izquierda
  Description: las coordenadas del tablero inician en el punto (0, 0)
    Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
    When elijo casilla con la coordenada x menor que 0
    Then se pide otra ficha
    And se informa el error

  Scenario: celda inexistente en el eje x hacia la derecha
  Description: las coordenadas del tablero inician en el punto (0, 0) y terminan en el punto (2, 2)
    Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
    When elijo casilla con la coordenada x mayor que 2
    Then se pide otra ficha
    And se informa el error