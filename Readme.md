# Animals - API testing
Test Automation coding challenge :muscle:

# Exercise 1: 
Write automated API tests in which you will add a new animal.

Write an invalid API test in which you will get an error.
Write assertions for them.
Please include a README.md file.
Publish your solution on your private GitHub/GitLab/BitBucket repositoryentation/v2

Available methods:
![img.png](img.png)


**URL** : https://retoolapi.dev/Zaf5SG/animals
## Pre-requirements
- **Package manager:** Meaven 3.9.6
- **Language:**  Java 8
- **Test framework:** Junit 4.13.2
- **Test library:** RestAssured 5.4.0

## Dependencies
All project dependencies are stored in <b>pom.xml</b> file

## Installation: 
### Meaven dependencies:
```bash
mvn clean install
```
### Run all test suites:
```bash
mvn surefire-report:report
```
Test report is place on:  **~/target/site/surefire-report.html**

### Run test suites by @Tag annotation:
```bash
mvn test -Dgroups="<TAG_NAME>" surefire-report:report-only 
```

Existing Tags: CRUD | SMOKE

**Remember to terminate the reports data from previous tests execution stored in: ~/target/surefire-reports**



# Exercice 2:
Write test scenarios for a given feature of your choice for mobile application. For example, Spotify - creating custom playlists. You can choose one of your favorite apps from UberEats/Glovo/Bolt/Wolt/Spotify.

I prepared a simple test scenarios using Gherking styles. I divided test cases based on Android/iPhone and particular test types.
All test cases are placed in: **~/src/test/resources/SpotifyProject**


# ENJOY :) 