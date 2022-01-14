#3 en Raya
  Feature: Como usuario quiero poner una ficha y comprobar si hay 3 en raya
    Scenario: celda libre
      Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
      When elijo casilla (0, 0)
      And no hay ninguna ficha colocada en esa posicion
      Then Pone ficha en la posicion seleccionada
      And como no hay 3 en raya, cambio de turno de jugador

    Scenario: celda ocupada
      Given un tablero de 3x3 con una ficha colocada en la posicion (1, 1)
      # - - -
      # - X O
      # - - -
      When elijo casilla (1, 1)
      And hay ninguna ficha colocada en esa posicion
      Then Se pide otra ficha


    ###############Ingreso de fichas en posiciones no válidas

    Scenario: celda inexistente en el eje x hacia la izquierda
      Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
      When elijo casilla con la coordenada x menor que 0
      Then se pide otra ficha
      And se informa el error

    Scenario: celda inexistente en el eje x hacia la derecha
      Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
      When elijo casilla con la coordenada x mayor que 2
      Then se pide otra ficha
      And se informa el error

    Scenario: celda inexistente en el eje y hacia arriba
      Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
      When elijo casilla con la coordenada y menor que 0
      Then se pide otra ficha
      And se informa el error

    Scenario: celda inexistente en el eje y hacia abajo
      Given un tablero de 3x3
      # - - -
      # - - -
      # - - -
      When elijo casilla con la coordenada y mayor que 2
      Then se pide otra ficha
      And se informa el error

      ############### Fin de ingreso de fichas en posiciones no válidas

      ############### GANAR EL JUEGO

    Scenario: ganar el juego en forma diagonal
      Given un tablero de 3x3 con las siguientes fichas
      # - O X
      # - X O
      # - - -
      When es el turno del jugador que juega con X
      And elige las coordenadas (0, 2)
      Then se coloca la ficha
      And como tiene 3 en raya en diagonal, se informa que gano

    Scenario: ganar el juego en forma diagonal invertida
      Given un tablero de 3x3 con las siguientes fichas
      # O X -
      # - O X
      # - - -
      When es el turno del jugador que juega con O
      And elige las coordenadas (2, 2)
      Then se coloca la ficha
      And como tiene 3 en raya en diagonal invertida, se informa que gano

    Scenario: ganar el juego en forma horizontal
      Given un tablero de 3x3 con las siguientes fichas
      # - O O
      # - X -
      # - X -
      When es el turno del jugador que juega con O
      And elige las coordenadas (0, 0)
      Then se coloca la ficha
      And como tiene 3 en raya en horizontal, se informa que gano

    Scenario: ganar el juego en forma vertical
      Given un tablero de 3x3 con las siguientes fichas
      # O X -
      # - X O
      # - - -
      When es el turno del jugador que juega con X
      And elige las coordenadas (1, 2)
      Then se coloca la ficha
      And como tiene 3 en raya en diagonal, se informa que gano

      ############### FIN GANAR EL JUEGO

    Scenario: fin del juego en empate
      Given un tablero de 3x3 sin ninguna celda libre y sin 3 fichas iguales de forma consecutiva
      # X O X
      # O X O
      # O X O
      When es el turno de un jugador
      And no hay mas celdas libres
      And no hay 3 en raya de ningun tipo
      Then se termina el juego
      And se informa de que hubo un empate

