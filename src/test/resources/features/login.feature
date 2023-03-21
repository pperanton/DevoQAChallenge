@login
@exercise1
Feature: feature to validate two scenarios from login page (https://eu.devo.com/login) as requested on DevoQAChallenge requirements.

  Scenario Outline: successful login (loginOK)
    Given user is on login page
    And user enters valid <username> and <password>
    When click on login button
    Then user navigates to home page
    Examples:
      | username | password |
      |pperanton@gmail.com|devocodechallenge|

  Scenario Outline: unsuccessful login (loginNOTOK)
    Given user is on login page
    And user enters invalid <username> and <password>
    When click on login button
    Then error popup is displayed
    Examples:
      | username | password |
      |pedro@gmail.com|notOkPassword|


  #Scenario: FreeTrial link works
  #Given user is on login page
  #When user clicks on "Empieza la prueba gratuita"
  #Then user navigates to free-trial page

  #JUSTIFICATION: I decided to design the above scenarios since they are the most valuable from a functional perspective.
  #For any other test to work inside the devo webpage, we must be able to access into the web itself. So the OK and NOOK scenarios
  #are mandatory from the automation side of the project. Furthermore, the FreeTrail links working scenario is also important but
  #from a business perspective, since any potential user should be able to access that link and create a free trial profile to
  #take a glimpse into the product itself. I would also like to add that the "Forgot password" scenario is also important functionality wide
  #but since it needs to perform an outside check (email verification and so) I wanted to focus on the functional side of the login page.
  #Also mention that some others tests would be nice to have like the red margin on login/password fields when information is not ok.

