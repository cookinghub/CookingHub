Feature: List of Recipes can be retrieved
  Scenario: client makes call to GET /recipes
    When the client calls /recipes
    Then the client receives status code of 200
    And the client receives an empty list