Feature: Playlist UI tests

  Basic UI tests for playlist UI. I'm showing the basic concepts for this type of tests.

  Background:
    Given User contains a valid account in Spotify mobile app


  Scenario Outline: Verify "Your Library" UI page components on various iPhone models with various screen size

    Given user is log in using '<iphone_model> in iOS 17'
    When  user open "Your Library"
    Then  The main components are displayed in appropraite resolution (bottom toolbar, menu list icons, music play icon, back arrow)

    Examples:
      | iphone_model |
      | '11 Pro Max' |
      | '11 Pro .'   |
      | 'XR'         |
      | '...'         |

