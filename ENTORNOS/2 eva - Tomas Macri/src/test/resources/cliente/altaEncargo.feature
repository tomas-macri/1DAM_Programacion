#Para los encargos, ademas de la foto, dinero, habilidad y estado
# tambien se guardará el nombre del sicario que lo tenga asignado
# o "" cuando no esté asignado. Esto es para poder saber si al dar
# de baja un sicario este tenía encargos que hay que renombrar o no.
# Tambien se le asignara un id al encargo para que el administrador
# pueda poder validar el encargo a la hora de que un sicario se lo asigne.
Feature: Como cliente quiero dar de alta un encargo para deshacerme de alguien que me cae mal

  Background:
    Given una lista de encargos=[{E001, 95KB, 1000, 1, "entrada", ""},{E002,50KB, 2000, 2, "validado", "sic2"}]

  Scenario: agregar exitosamente
    Given una foto = 90KB
    And un dinero = 3000
    And un nivel de habilidad = 3
    When la foto pesa menos de 100KB
    And el dinero es mayor que 1000
    And el nivel de habilidad es mayor que 0
    And el nivel de habilidad es menor que 6
    Then se crea un nuevo encargo={90KB, 3000, 3, "entrada",""}
    And se agrega el encargo a la lista de encargos
    And la lista de encargos queda=[{E001,95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"},{E003, 90KB, 3000, 3, "entrada",""}]

  Scenario: foto muy pesada
    Given una foto = 110KB
    And un dinero = 3000
    And un nivel de habilidad = 3
    When la foto pesa mas de 100KB
    And el dinero es mayor que 1000
    And el nivel de habilidad es mayor que 0
    And el nivel de habilidad es menor que 6
    Then no se crea un encargo
    And se informa del error
    And la lista de encargos queda=[{E001,95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]

  Scenario: dinero muy bajo
    Given una foto = 100KB
    And un dinero = 100
    And un nivel de habilidad = 3
    When la foto pesa menos de 100KB
    And el dinero es menor que 1000
    And el nivel de habilidad es mayor que 0
    And el nivel de habilidad es menor que 6
    Then no se crea un encargo
    And se informa del error
    And la lista de encargos queda=[{E001,95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]

  Scenario: nivel de habilidad menor al permitido
    Given una foto = 90KB
    And un dinero = 1000
    And un nivel de habilidad = 0
    When la foto pesa menos de 100KB
    And el dinero es mayor que 1000
    And el nivel de habilidad es menor o igual que 0
    And el nivel de habilidad es menor que 6
    Then no se crea un encargo
    And se informa del error
    And la lista de encargos queda=[{E001,95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]

  Scenario: nivel de habilidad mayor al permitido
    Given una foto = 90KB
    And un dinero = 1000
    And un nivel de habilidad = 66
    When la foto pesa menos de 100KB
    And el dinero es mayor que 1000
    And el nivel de habilidad es mayor que 0
    And el nivel de habilidad es mayor que 5
    Then no se crea un encargo
    And se informa del error
    And la lista de encargos queda=[{E001,95KB, 1000, 1, "entrada", ""},{E002, 50KB, 2000, 2, "validado", "sic2"}]
