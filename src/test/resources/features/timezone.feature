@timezone
@exercise1
Feature: feature that validates the timezone feature within the web as requested on DevoQAChallenge requirements.

  Background: user logs into home page
    Given user goes to login page
    And user login using valid credentials and click login button

  Scenario Outline: Timezone changed only for current session
    When user is on home page
    And user clicks on Timezone option
    And user selects a new <Timezone> for current session only
    Then <Timezone> selection must be reflected on user left banner
    Examples:
      | Timezone |
      | New York |
      | Tokyo    |

