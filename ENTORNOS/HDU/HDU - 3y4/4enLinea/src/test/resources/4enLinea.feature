#4 en Línea
Feature: Como usuario quiero poner una ficha y comprobar si hay 4 en línea
  Background:
    Given es el turno del jugador que usa X

  Scenario: celda libre
    Given un tablero de 7x6
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -

    When elijo la columna 0
    And la columna tiene espacios vacios
    Then Suelta la ficha en la columna seleccionada
    And la ficha cae hasta que se encuentra con el final del tablero o con otra ficha
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # X - - - - - -
    And como no hay 4 en linea, cambio de turno de jugador


  Scenario: celda ocupada
    Given un tablero de 7x6 con la columna 6 completa
      # - - - - - - X
      # - - - - - - O
      # - - - - - - O
      # - - - - - - X
      # - - - - - - O
      # - - - - - - X
    When elijo la columna 6
    Then Se pide otra ficha
    And se informa de que la columna esta completa

  ###############Ingreso de fichas en posiciones no válidas

  Scenario: celda inexistente en el eje x hacia la izquierda
  Description: las columnas del tablero van desde la 0 hasta la 6
    Given un tablero de 7x6
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - O - - -
      # - - - X - - -
      # X O X O - - -
    When elijo casilla con la coordenada x menor que 0
    Then se pide otra ficha
    And se informa el error

  Scenario: celda inexistente en el eje x hacia la derecha
  Description: las columnas del tablero van desde la 0 hasta la 6
    Given un tablero de 7x6
      # - - -
      # - - -
      # - - -
    When elijo casilla con la coordenada x mayor que 6
    Then se pide otra ficha
    And se informa el error

  ###############Fin Ingreso de fichas en posiciones no válidas

 ############### GANAR EL JUEGO

  Scenario: ganar el juego en forma diagonal
    Given un tablero de 7x6 con las siguientes fichas
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - - O X O -
      # - - - X O O -
      # X O X O O X X
    When elige la columna 5
    Then se coloca la ficha
    And como tiene 4 en linea en diagonal, se informa que gano el jugador que usa X

  Scenario: ganar el juego en forma diagonal invertida
    Given un tablero de 7x6 con las siguientes fichas
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - O X O - -
      # - - X O X O -
      # X O X O O X X
    When elige la columna 2
    Then se coloca la ficha
    And como tiene 4 en linea en diagonal invertida, se informa que gano el jugador que usa X

  Scenario: ganar el juego en forma vertical
    Given un tablero de 7x6 con las siguientes fichas
      # - - - - - - -
      # - - - - - - -
      # - - - X - - -
      # - - O X O - -
      # - O X X X O O
      # - O X O O X X
    When elige la columna 3
    Then se coloca la ficha
    And como tiene 4 en linea en vertical, se informa que gano el jugador que usa X

  Scenario: ganar el juego en forma horizontal
    Given un tablero de 7x6 con las siguientes fichas
      # - - - - - - -
      # - - - - - - -
      # - - - - - - -
      # - - O X O - -
      # - O X X X - O
      # - O X O O X X
    When elige la columna 5
    Then se coloca la ficha
    And como tiene 4 en raya en horizontal, se informa que gano el jugador que usa X

      ############### FIN GANAR EL JUEGO

  Scenario: fin del juego en empate
    Given un tablero de 7x6 con las siguientes fichas
      # X O X X O X X
      # O X O O X O X
      # O X O X O X X
      # X X O X O X O
      # O O X X X O O
      # X O X O O X X
    When no hay mas celdas libres
    And no hay 4 en linea de ningun tipo
    Then se termina el juego
    And se informa de que hubo un empate

