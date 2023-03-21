@jobRequest
@exercise2
Feature: remove a query from the EU region

  Scenario: remove a query from the EU region
    Given job endpoint exists
    When I send a valid job request
    Then validations on the response must be OK