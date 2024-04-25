Feature: User Login

  Basic smoke tests for login cases. I'm showing the basic concepts for this type of tests.

  Background:
    Given User contains a valid account in Spotify mobile app
    And User has a valid google email account
    And The Spotify mobile app is installed on the smartphone
    And The network connection is stable
    And The mobile app is opened
    And User is not logged into app


  Scenario Outline: User login in Spotify mobile app by typing entering username and password and log out

    Given User choose standard login
    When  User log in by entering credentials for '<account_type>'
    And The main page show up
    And The bottom toolbar is enabled
    And User move into "Setting nad privacy"
    And User log out from app
    Then Login page show up

    Examples:
      | account_type |
      | Standard |
      | Premium  |

  Scenario Outline: User login in using saved Google Smart Lock

    Given User choose standard login
    When User choose a Spotify <account_type> saved with Google Smart Lock
    And The main page show up
    And The bottom toolbar is enabled
    And User move into "Setting nad privacy"
    And User log out from app
    Then Login page show up

    Examples:
      | account_type |
      | Standard |
      | Premium  |