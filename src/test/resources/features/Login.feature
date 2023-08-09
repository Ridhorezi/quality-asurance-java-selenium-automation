@All
Feature: Login

  @TestLogin
  Scenario: Login
    Given User is on login page
    When User fill username and password
    And user click login button
    Then User verify login result
