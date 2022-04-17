Feature: Como usuario quiero agregarle goles a un equipo en un partido determinado
  Background:
    Given un Partido partido={Equipo equipo1{nombre: "Argentina", goles:2}, golesEq1=-1, Equipo equipo2={nombre: "Arabia Saudí, goles:"1"}, golesEq2:1}
    And el usuario selecciona el Equipo equipo{nombre: "Argentina", goles:2}

    Feature: Agregar exitosamente
      Given golesEq1=2
      And goles es mayor o igual que 0
      And goles menor que 10
      When el usuario pulsa "aceptar"
      Then se actualiza el valor de golesEq1 a 2
      And el Equipo equipo1={nombre: "Argentina", goles:2} se actualiza a equipo1={nombre: "Argentina", goles:4}

    Feature: La cantidad de goles es un número negativo
      Given golesEq1=-2
      And golesEq1 es menor que 0
      When el usuario pulsa "aceptar"
      Then se muestra un alert con el mensaje="Introduzca una cantidad de goles entre 1 y 10"
      And golesEq1=-1
      And equipo1={nombre: "Argentina", goles:2}

    Feature: La cantidad de goles es un número demasiado grande
    Given golesEq1=11
    And golesEq1 es mayor que 9
    When el usuario pulsa "aceptar"
    Then se muestra un alert con el mensaje="Introduzca una cantidad de goles entre 1 y 9"
    And golesEq1=-1
    And equipo1={nombre: "Argentina", goles:2}