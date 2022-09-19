Feature: Como usuario quiero avanzar a la fase de eliminación al terminar la fase de grupos para poder seguir con el mundial
  Background:
    Given equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4, puntos:3}, Equipo={nombre:"Uruguay", goles:5, invictos:1, puntos: 3}, Equipo={nombre:"Croacia", goles:5, invictos:2, puntos:1}, Equipo={nombre:"Brasil", goles:3, invictos:1, puntos:1}, Equipo={nombre:"España", goles:9, invictos:4, puntos:7}, Equipo={nombre:"Arabia Saudí", goles:5, invictos:1, puntos: 7}, Equipo={nombre:"Polonia", goles:5, invictos:2, puntos:1}, Equipo={nombre:"Ecuador", goles:3, invictos:1, puntos:1}]
    And una lista de Grupos=[Grupo={nombre:"A", equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4, puntos:7}, Equipo={nombre:"Uruguay", goles:5, invictos:1, puntos: 7}, Equipo={nombre:"Croacia", goles:5, invictos:2, puntos:3}, Equipo={nombre:"Brasil", goles:3, invictos:1, puntos:0}]}, Grupo={nombre:"A", equipos=[Equipo={nombre:"España", goles:9, invictos:4, puntos:7}, Equipo={nombre:"Arabia Saudí", goles:5, invictos:1, puntos: 7}, Equipo={nombre:"Polonia", goles:5, invictos:2, puntos:1}, Equipo={nombre:"Ecuador", goles:3, invictos:1, puntos:1}]}]

    Scenario: Avanzar exitosamente
      Given jornadasJugadas=3
      When se pulsa "ir a la fase de elimnatación"
      Then se actualiza la lista de Equipos con los primeros dos equipos de cada grupo de la lista de Grupos
      And la lista de equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4, puntos:3}, Equipo={nombre:"Uruguay", goles:5, invictos:1, puntos: 3}, Equipo={nombre:"España", goles:9, invictos:4, puntos:7}, Equipo={nombre:"Arabia Saudí", goles:5, invictos:1, puntos: 7}]
      And se va a la patnalla de eliminación

      Scenario: Quedan jornadas por jugar
        Given jornadasJugadas=2
        And jornadasJugadas<3
        When se pulsa "ir a la fase de elimnatación"
        Then se informa que quedan partidos por jugarseç
        And no se avanza a la fase de eliminación