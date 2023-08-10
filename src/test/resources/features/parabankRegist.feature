@All
Feature: Para bank Registration

  @positive @Register
  Scenario: Success register
    Given User is on parabank homepage
    When User click register link button
    Then User is in register page
    When User input name
    And User input address detail
    And User fill valid username and password
    And User input password confirmation
    When user click register button
    Then User register successfully

  @negative @Register
  Scenario: Failed register - Mismatch Password
    Given User is on parabank homepage
    When User click register link button
    Then User is in register page
    When User input name
    And User input address detail
    And User fill valid username and password
    And User input invalid password confirmation
    When user click register button
    Then User get error password did not match
