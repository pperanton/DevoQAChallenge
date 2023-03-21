@queryRequest
@exercise2
Feature: get data from a query from the EU region.

  Scenario: get data from a query from the EU region
    Given query endpoint exists
    When I send a valid query request
    Then validations on the response should be OK
