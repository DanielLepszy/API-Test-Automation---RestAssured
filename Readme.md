# Animals - API testing
Test Automation coding challenge :muscle:

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


# Exercise:
1. Write automated API tests in which you will add a new animal.

Write an invalid API test in which you will get an error.
Write assertions for them.
Please include a README.md file.
Publish your solution on your private GitHub/GitLab/BitBucket repositoryentation/v2 

Available methods:
![img.png](img.png)