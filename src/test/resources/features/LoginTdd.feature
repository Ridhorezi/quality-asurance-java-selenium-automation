@All
Feature: Login with TDD

  @TDD
  Scenario Outline: Login with TDD
    Given User is on login page
    When User input <username> and <password>
    And user click login button
    Then User get verify login <results>

    Examples:
    | username | password | results |
    | standard_user | secret_sauce | Passed |
    | wrong_username | secret_sauce | Failed|
    | standard_user | wrong_password | Failed |

