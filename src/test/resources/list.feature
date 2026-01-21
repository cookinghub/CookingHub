Feature: List of Recipes can be retrieved
  Background:
    Given no recipes are in the DB

  Scenario: client makes call to GET /recipes with no recipes
    When the client calls /recipes
    Then the client receives status code of 200
    And the client receives an empty list

  Scenario: client adds recipe and then GETs it
    Given the client adds a recipe named fancy-recipe
    When the client retrieves the new recipe
    Then the recipe just added is returned