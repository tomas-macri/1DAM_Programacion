Feature: Como usuario quiero ver una lista de los países con mayor porterías a 0 para saber a qué países debo atacar más

  Scenario: No hay dos países con las mismas porterías a 0
    Given una lista de equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4}, Equipo={nombre:"Brasil", goles:5, invictos:1}, Equipo={nombre:"Uruguay", goles:3, invictos:2}]
    And no hay dos países con las mismas porterías a 0
    When el usuario presiona "Ver los países con mayor porterías a 0"
    Then se muestra la lista ordenada de los países con mayor porterías a 0
    And la lista de equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4}, Equipo={nombre:"Uruguay", goles:3, invictos:2}, Equipo={nombre:"Brasil", goles:5, invictos:1}}]

  Scenario: Hay dos países con las mismas porterías a 0
    Given una lista de equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4}, Equipo={nombre:"Uruguay", goles:5, invictos:1}, Equipo={nombre:"Brasil", goles:3, invictos:1}]
    And hay dos países con las mismas porterías a 0
    When el usuario presiona "Ver los países con mayor porterías a 0"
    Then se muestra la lista ordenada de los países con mayor porterías a 0
    And ordenada por orden alfabético en caso de igualdad de porterías a 0
    And la lista de equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4}, Equipo={nombre:"Brasil", goles:3, invictos:1}, Equipo={nombre:"Uruguay", goles:5, invictos:1}}]