@All
Feature: Login

  @TestLogin1 @Positive @Login
  Scenario: Login
    Given User is on login page
    When User fill username and password
    And user click login button
    Then User verify login result

  @TestLogin2 @Negative @Login
  Scenario: Login
    Given User is on login page
    When User fill invalid username and password
    And user click login button
    Then User get error message
