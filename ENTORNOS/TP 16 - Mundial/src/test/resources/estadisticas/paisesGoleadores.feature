Feature: Como usuario quiero ver una tabla con los países más goleadores para ver a que selecciones prefiero evitar
  Scenario: No hay países con la misma cantidad de goles
        Given Una lista de Equipos=[Equipo={nombre:'Argentina', goles:9, invictos:2}, Equipo={nombre:'Brasil', goles:8, invictos:2}, Equipo={nombre:'Uruguay', goles:7, invictos:2}, Equipo={nombre:'España', goles:6, invictos:2}, Equipo={nombre:'Alemania', goles:5, invictos:2}, Equipo={nombre:'Portugal', goles:4, invictos:2}, Equipo={nombre:'Inglaterra', goles:0, invictos:2}, Equipo={nombre:'Ecuador', goles:3, invictos:2}, Equipo={nombre:'Francia', goles:1, invictos:2}]
        And No hay dos equipos con la misma cantidad de goles
        When El usuario selecciona "Ver los equipos más goleadores"
        Then Se muestra la lista de equipos ordenada de mayor a menor cantidad de goles
        And La lista se muestra en la siguiente forma:  Equipos=[Equipo={nombre:'Argentina', goles:9, invictos:2}, Equipo={nombre:'Brasil', goles:8, invictos:2}, Equipo={nombre:'Uruguay', goles:7, invictos:2}, Equipo={nombre:'España', goles:6, invictos:2}, Equipo={nombre:'Alemania', goles:5, invictos:2}, Equipo={nombre:'Portugal', goles:4, invictos:2},  Equipo={nombre:'Ecuador', goles:3, invictos:2}, Equipo={nombre:'Francia', goles:1, invictos:2}, Equipo={nombre:'Inglaterra', goles:0, invictos:2}]

  Scenario: No hay países con la misma cantidad de goles
    Given Una lista de Equipos=[Equipo={nombre:'Brasil', goles:9, invictos:2}, Equipo={nombre:'Argentina', goles:9, invictos:2}, Equipo={nombre:'Uruguay', goles:7, invictos:2}, Equipo={nombre:'España', goles:6, invictos:2}, Equipo={nombre:'Alemania', goles:5, invictos:2}, Equipo={nombre:'Portugal', goles:4, invictos:2}, Equipo={nombre:'Inglaterra', goles:0, invictos:2}, Equipo={nombre:'Ecuador', goles:3, invictos:2}, Equipo={nombre:'Francia', goles:1, invictos:2}]
    And Hay dos equipos con la misma cantidad de goles
    When El usuario selecciona "Ver los equipos más goleadores"
    Then Se muestra la lista de equipos ordenada de mayor a menor cantidad de goles
    And Ordenada por orden alfabético en caso de empate en cantidad de goles
    And La lista se muestra en la siguiente forma:  Equipos=[Equipo={nombre:'Argentina', goles:9, invictos:2}, Equipo={nombre:'Brasil', goles:8, invictos:2}, Equipo={nombre:'Uruguay', goles:7, invictos:2}, Equipo={nombre:'España', goles:6, invictos:2}, Equipo={nombre:'Alemania', goles:5, invictos:2}, Equipo={nombre:'Portugal', goles:4, invictos:2},  Equipo={nombre:'Ecuador', goles:3, invictos:2}, Equipo={nombre:'Francia', goles:1, invictos:2}, Equipo={nombre:'Inglaterra', goles:0, invictos:2}]

