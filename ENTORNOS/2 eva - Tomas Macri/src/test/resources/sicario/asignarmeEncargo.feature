#Ver encargos filtrados y asignarselos
Feature: Como sicario quiero ver la lista de encargos que me podría asignar para poder empezar un trabajo

  Background:
    Given un sicario={"sic4", "zona1", 4, 4000}
    And una lista de encargos=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]
    And una lista filtrada de encargos=[{E001, 95KB, 1000, 1, "entrada"}]

    Scenario: Asignarse exitosamente
      When el sicario ingresa un id de encargo=E001
      And hay un encargo con ese id en la lista de encargos
      And el estado de ese encargo="entrada"
      And el nivel de ese encargo es menor que el nivel del sicario (1)
      Then se cambia el estado del encargo a "esperando-confirmacion"
      And se guarda el nombre del sicario que se asigna el encargo
      And la lista de encargos queda=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

      Scenario: Encargo previamente asignado
        When el sicario ingresa un id de encargo=E003
        And hay un encargo con ese id en la lista de encargos
        And el estado de ese encargo no es ="entrada"
        And el nivel de ese encargo es menor que el nivel del sicario (3)
        Then no se cambia el estado del encargo
        And se informa de que ese encargo no esta disponible.
        And la lista de encargos queda=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]

        Scenario: Nivel muy alto para el sicario
          When el sicario ingresa un id de encargo=E004
          And hay un encargo con ese id en la lista de encargos
          And el estado de ese encargo="entrada"
          And el nivel de ese encargo es mayor que el nivel del sicario (5)
          Then no se cambia el estado del encargo
          And se informa de que ese encargo no esta disponible.
          And la lista de encargos queda=[{E001, 95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "esperando-confirmacion","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}]


