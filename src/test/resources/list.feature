Feature: List of Recipes can be retrieved
  Scenario: client makes call to GET /recipes
    When the client calls /recipes
    Then the client receives status code of 200
    And the client receives an empty list
  Scenario: client adds recipe and then GETs it
    Given the client adds a recipe
    When the client retreives the new recipe
    Then the recipe is returned