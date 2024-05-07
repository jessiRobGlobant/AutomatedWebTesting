# Automated Web Tesing

This project is designed to automate testing of the https://www.saucedemo.com/ page using Selenium 4 and TestNG.

## Project Structure

The project is organized into the following folders:

- **main**: This folder contains the following subfolders and files:
  - **enum**: This folder stores all the data required for the tests, such as user details and error messages.
  - **driver**: This folder contains the driver configuration files for Selenium.
  - **pages**: This folder contains the page elements and interactions, which are used by the test scripts.

- **test**: This folder contains the following subfolders and files:
  - **automation**: This folder stores the test definitions, where the actual test scripts are written.
  - **resources**: This folder contains the different enviorment suites to test

## Test Suites

The project includes different test suites, which can be executed separately. These suites are located in the `resources` folder inside the `test` directory. The available test suites are:

- **Regression**: This suite includes a comprehensive set of tests that cover all major functionalities of the application.
- **Sanity**: This suite includes a subset of tests that focus on the most critical functionalities to ensure basic system stability.
- **Smoke**: This suite includes a minimal set of tests that quickly verify if the major functionalities of the application are working as expected.

## Prerequisites

Before running the tests, ensure that you have the following installed:

- Java Development Kit (JDK)
- Selenium 4
- TestNG
- Web browser driver (for example, ChromeDriver)

## How to Run

To execute the tests, follow these steps:

1. Clone the repository to your local machine.
2. Set up the required dependencies and ensure they are properly configured.
3. Open the project in your preferred Java IDE.
4. Locate the respective test suite XML file in the `resources` folder.
5. Right-click on the XML file and select "Run as TestNG Suite" (or use the command line to run the suite).
