# test-task-selenium
Completed test task for job application.

## How to run tests
Make sure you have installed:
- Java 17+ (or compatible with your Maven setup)
- Maven
- Allure 2.36.0

Run tests using Maven:
mvn clean test "-Dallure.results.directory=target/allure-results"

After execution, Allure result files will be generated in:
target/allure-results

## Allure report generation
Use Allure command line:
allure generate target/allure-results --clean -o target/allure-report

Then open:
target/allure-report/index.html
