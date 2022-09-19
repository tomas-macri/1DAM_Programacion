Feature: Como usuario quiero avanzar de ronda de eliminación directa para continuar con el mundial
  Scenario: Avanzar exitosamente
    Given una lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And una lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:7}]
    And golesEq1!=-1 en todos los partidos de la lista
    And golesEq2!=-1 en todos los partidos de la lista
    And golesEq1!=golesEq2 en cada partido
    When el usuario pulsa "Avanzar"
    Then se eliminan los equipos con menos goles en cada partido de la lista de Equipos
    And la lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And la lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=-1, Equipo equipo2={nombre: "Alemania", goles:2, invictos:2}, golesEq2=-1}]

  Scenario: Hay partidos igualados
    Given una lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And una lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=2, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:2}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:7}]
    And golesEq1!=-1 en todos los partidos de la lista
    And golesEq2!=-1 en todos los partidos de la lista
    And golesEq1=golesEq2 en uno o más partidos
    When el usuario pulsa "Avanzar"
    Then se informa que queda desempatar el resultado de uno o más partidos
    And la lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And la lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:-1}]

  Scenario: Al equipo2 de uno o más partidos no se le asignó los goles marcados
    Given una lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And una lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:-1}]
    And golesEq1!=-1 en todos los partidos de la lista
    And golesEq2=-1 en uno o más partidos de la lista
    When el usuario pulsa "Avanzar"
    Then se informa que queda definir el resultado de un partido
    And la lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And la lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:-1}]

  Scenario: Al equipo1 de uno o más partidos no se le asignó los goles marcados
    Given una lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And una lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=-1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:7}]
    And golesEq1=-1 en uno o más partidos de la lista
    And golesEq2!=-1 en todos los partidos de la lista
    When el usuario pulsa "Avanzar"
    Then se informa que queda definir el resultado de un partido
    And la lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And la lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=-1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:7}]