Feature: Spotify playlist system test cases

  Basic integration tests for playlist. I'm showing the basic concepts for this type of tests.

  Background:
    Given user contains a valid account in Spotify mobile app
    And user is logged in using Android 14.0
#
  Scenario: User create new playlist with unstable Wi-fi connection

    Given user open "Your Library"
    And The Wi-fi  connection is unstable
    When user try to add new song to playlist
    Then the notification about unstable connection show up
    And the application stop runs
    And the song will to be saved into playlist

  Scenario: User create new playlist during switching from one Wi-fi connection to another

    Given user open "Your Library"
    And The Wi-fi connection is switching to another one
    When user try to add new song to playlist
    Then the song should be saved into playlist
    And application has no critical latency

  Scenario: User create new playlist with low battery level

    Given user open "Your Library"
    And The battery level is low
    When user try to add new song to playlist
    And the smartphone turn off due to low battery level
    Then the song will to be saved into playlist

  Scenario: User create new playlist with with loaded smartphone

    Given the NFC, Wi-fi, Bluetooth, GPS and other mobile features are turned on
    And the device has no free RAM memory
    And user open "Your Library"
    When user try to add new song to playlist
    Then the song should be saved into playlist without any delays.
