Feature: Como administrador quiero ver mi lista de encargos filtradas para ver bien la situacion de cada encargo

  Background:
    Given una lista de encargos=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"},{E002, 50KB, 2000, 2, "validado", "sic4"},{E003, 90KB, 3000, 3, "cumplido","sic3"}, {E004, 95KB, 1000, 5, "entrada", ""}, {E005, 95KB, 1000, 1, "pagado", "sic1"}, {E006, 80KB, 5000, 1, "cumplido", "sic1"}]
    And una lista de sicarios=[{"sic1", "zona1", 3, 1000},{"sic2", "zona1", 2, 2000},{"sic3", "zona1", 3, 3000},{"sic4", "zona1", 4, 4000},{"sic5", "zona1", 5, 5000}]

  Scenario: Filtrar por "entrada" exitosamente
    Given nombre del filtro="entrada"
    When hay encargos con ese estado
    Then se muestra la lista=[{E004, 95KB, 1000, 5, "entrada", ""}]

  Scenario: Filtrar por "esperando-confirmacion" exitosamente
    Given nombre del filtro="esperando-confirmacion"
    When hay encargos con ese estado
    Then se muestra la lista=[{E001, 95KB, 1000, 1, "esperando-confirmacion", "sic4"}]

  Scenario: Filtrar por "pagado" exitosamente
    Given nombre del filtro="pagado"
    When hay encargos con ese estado
    Then se muestra la lista=[{E005, 95KB, 1000, 1, "pagado", "sic1"}]

  Scenario: Filtrar por "validado" exitosamente
    Given nombre del filtro="validado"
    When hay encargos con ese estado
    Then se muestra la lista=[{E002, 50KB, 2000, 2, "validado", "sic4"}]

  Scenario: Filtrar por "cumplido" exitosamente
    Given nombre del filtro="cumplido"
    When hay encargos con ese estado
    Then se muestra la lista=[{E003, 90KB, 3000, 3, "cumplido","sic3"},{E006, 80KB, 5000, 1, "cumplido", "sic1"}]

  Scenario: Filtro vacio
    Given nombre del filtro="muertos"
    When no hay encargos con ese estado
    Then se informa que no hay encargos con ese estado
    And no se muestra una lista

