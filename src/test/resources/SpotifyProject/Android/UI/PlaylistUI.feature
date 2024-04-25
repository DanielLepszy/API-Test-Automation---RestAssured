Feature: Playlist UI tests

  Basic UI tests for playlist UI. I'm showing the basic concepts for this type of tests.

  Background:
    Given User contains a valid account in Spotify mobile app


  Scenario Outline: Verify "Your Library" UI page components on various Android devices with various screen size

    Given user is log in using '<android_model> 14 version'
    When  user open "Your Library"
    Then  The main components are displayed in appropraite resolution (bottom toolbar, menu list icons, music play icon, back arrow)

    Examples:
      | android_model     |
      | 'Galaxy S23 FE'   |
      | 'Galaxy Z Fold 5' |
      | 'Galaxy S21 FE'   |
      | '...'             |

