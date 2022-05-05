0Feature: Como usuario quiero seleccionar un partido de la lista de partidos para modificar el resultado
  Background:
    Given una lista de Equipos=[Equipo={nombre: "Argentina", goles:2, invictos:2}, Equipo={nombre: "Arabia Saudí", goles:1, invictos:0}, Equipo={nombre: "Brasil", goles:2, invictos:2}, Equipo={nombre: "Alemania", goles:2, invictos:2}]
    And una lista de partidos=[Partido={Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1}, Partido={Equipo equipo1{nombre: "Brasil", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Alemania", goles:8, invictos:5}, golesEq2:7}]

  Scenario: Seleccionar exitosamente
    Given el partido= {Equipo equipo1{nombre: "Argentina", goles:2, invictos:2}, golesEq1=1, Equipo equipo2={nombre: "Arabia Saudí", goles:1, invictos:0}, golesEq2:1} de la ista de partidos
    When se pulsa el boton de modificar resultado
    Then Se carga la pantalla con los datos del partido seleccionado

  Scenario: Seleccion vacia
    Given el partido= {}
    When se pulsa el boton de modificar resultado
    Then Se informa que no se ha seleccionado ningun partido
    And No se carga una pantalla de modificacion de resultado