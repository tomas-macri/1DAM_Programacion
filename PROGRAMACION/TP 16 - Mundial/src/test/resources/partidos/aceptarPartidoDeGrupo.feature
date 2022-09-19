Feature: Como usuario quiero actualizar la tabla de la fase de grupos al modificar un partido
  Background:
    Given Etapa="grupo"
    And una lista de Equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4, puntos:3}, Equipo={nombre:"Uruguay", goles:5, invictos:1, puntos: 3}, Equipo={nombre:"Croacia", goles:5, invictos:2, puntos:1}, Equipo={nombre:"Brasil", goles:3, invictos:1, puntos:1}]
    And un Grupo={nombre:"A", equipos=[Equipo={nombre:"Argentina", goles:9, invictos:4, puntos:3}, Equipo={nombre:"Uruguay", goles:5, invictos:1, puntos: 3}, Equipo={nombre:"Croacia", goles:5, invictos:2, puntos:1}, Equipo={nombre:"Brasil", goles:3, invictos:1, puntos:1}]}

    Scenario: Gana el equipo1 con goles en contra
      Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:2, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:1}
      And golesEq1 es distinto de -1
      And golesEq2 es distinto de -1
      And golesEq1>golesEq2
      When se pulsa "Aceptar moficaciones del partido"
      Then Grupo.equipos[0].puntos+=3
      And Grupo.equipos[1].puntos+=0
      And Grupo.equipos[0].goles+=golesEq1
      And Grupo.equipos[1].goles+=golesEq2
      And Grupo.equipos[0] = {nombre:"Argentina", goles:11, invictos:4, puntos:6}
      And Grupo.equipos[1] = {nombre:"Uruguay", goles:6, invictos:1, puntos:3}

  Scenario: Gana el equipo1 sin goles en contra
    Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:2, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:0}
    And golesEq1 es distinto de -1
    And golesEq2 es distinto de -1
    And golesEq1<golesEq2
    When se pulsa "Aceptar moficaciones del partido"
    Then Grupo.equipos[0].puntos+=3
    And Grupo.equipos[1].puntos+=0
    And Grupo.equipos[0].goles+=golesEq1
    And Grupo.equipos[1].goles+=golesEq2
    And Grupo.equipos[0].invictos++
    And Grupo.equipos[0] = {nombre:"Argentina", goles:11, invictos:5, puntos:6}
    And Grupo.equipos[1] = {nombre:"Uruguay", goles:5, invictos:1, puntos:3}


  Scenario: Gana el equipo2 con goles en contra
    Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:1, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:2}
    And golesEq1 es distinto de -1
    And golesEq2 es distinto de -1
    And golesEq1<golesEq2
    When se pulsa "Aceptar moficaciones del partido"
    Then Grupo.equipos[1].puntos+=0
    And Grupo.equipos[0].puntos+=3
    And Grupo.equipos[1].goles+=golesEq2
    And Grupo.equipos[0].goles+=golesEq1
    And Grupo.equipos[1] = {nombre:"Uruguay", goles:7, invictos:1, puntos:6}
    And Grupo.equipos[0] = {nombre:"Argentina", goles:10, invictos:4, puntos:3}

  Scenario: Gana el equipo2 sin goles en contra
    Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:2, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:2}
    And golesEq1 es distinto de -1
    And golesEq2 es distinto de -1
    And golesEq1=golesEq2
    When se pulsa "Aceptar moficaciones del partido"
    Then Grupo.equipos[0].puntos+=0
    And Grupo.equipos[1].puntos+=3
    And Grupo.equipos[0].goles+=golesEq1
    And Grupo.equipos[1].goles+=golesEq2
    And Grupo.equipos[1].invictos++
    And Grupo.equipos[0] = {nombre:"Argentina", goles:9, invictos:4, puntos:3}
    And Grupo.equipos[1] = {nombre:"Uruguay", goles:7, invictos:2, puntos:6}

    Scenario: Empate con goles
      Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:2, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:2}
      And golesEq1 es distinto de -1
      And golesEq2 es distinto de -1
      And golesEq1=golesEq2
      When se pulsa "Aceptar moficaciones del partido"
      Then Grupo.equipos[0].puntos++
      And Grupo.equipos[1].puntos++
      And Grupo.equipos[0].goles+=golesEq1
      And Grupo.equipos[1].goles+=golesEq2
      And Grupo.equipos[0] = {nombre:"Argentina", goles:11, invictos:4, puntos:4}
      And Grupo.equipos[1] = {nombre:"Uruguay", goles:7, invictos:1, puntos:4}

  Scenario: Empate sin goles
    Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:0, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:0}
    And golesEq1 es distinto de -1
    And golesEq2 es distinto de -1
    And golesEq1=golesEq2
    When se pulsa "Aceptar moficaciones del partido"
    Then Grupo.equipos[0].puntos++
    And Grupo.equipos[1].puntos++
    And Grupo.equipos[0].goles+=golesEq1
    And Grupo.equipos[1].goles+=golesEq2
    And Grupo.equipos[0].invictos++
    And Grupo.equipos[1].invictos++
    And Grupo.equipos[0] = {nombre:"Argentina", goles:9, invictos:5, puntos:4}
    And Grupo.equipos[1] = {nombre:"Uruguay", goles:5, invictos:2, puntos:4}

    Scenario: Goles del equipo1 no válidos
      Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:-1, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:2}
      And golesEq1 es -1
      When se pulsa "Aceptar moficaciones del partido"
      Then Se informa que los goles no son válidos
      And no se actualiza el grupo


  Scenario: Goles del equipo2 no válidos
    Given un Partido={equipo1={nombre:"Argentina", goles:9, invictos:4, puntos: 3}, golesEq1:1, equipo2={nombre:"Uruguay", goles:5, invictos:1, puntos:3}, golesEq2:-1}
    And golesEq2 es -1
    When se pulsa "Aceptar moficaciones del partido"
    Then Se informa que los goles no son válidos
    And no se actualiza el grupo