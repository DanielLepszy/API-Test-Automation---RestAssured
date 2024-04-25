Feature: Spotify playlist

  Basic integration tests for playlist. I'm showing the basic concepts for this type of tests.

  Background:
    Given user contains a valid account in Spotify mobile app
    And user is logged in using iPhone with iOS 17.1
    And The network connection is stable
    And Battery level is stable

  Scenario: User create new playlist

    Given user open "Your Library"
    And user click "+" button
    And user choose "Playlist"
    When user gives a valid playlist name
    Then the notification about creating new playlist show up on the page
    And the new playlist is created
    And the new playlist is visible in "Your Library"
    Then user search and open single song
    And user "Add song to the other playlist"
    And user mark a valid playlist name
    Then the notification about adding new song show up on the page
    And the song is added to playlist

  Scenario: User add new searched song into playlist

    Given user open "Search" tab
    When user search a valid songs
    And user attach song using "Add song to the other playlist"
    And user mark a valid playlist name
    Then the notification about adding new song show up on the page
    And the song is added to playlist

  Scenario: User add new song using "Add to this playlist" feature

    Given user open "Your Library"
    When  user click "Add song to the other playlist"
    And user search and choose a valid song from list
    And user click button to add song
    Then the notification about adding new song show up on the page
    And the song is added to playlist

  Scenario: User remove song from playlist

    Given user open "Your Library"
    And user open playlist
    When  user find and clik on single song
    And user click on "Remove from this playlist"
    Then the notification about removing song show up on the page
    And the song is removed from playlist

  Scenario: User delete entire playlist

    Given user open "Your Library"
    And user open playlist
    When user delete playlist using "X Delete playlist"
    And user confirm decision to delete playlist
    Then the playlist is remmoved
    And the song is removed from playlist


