# Exercise 19

## Overview

Exercise 19 is a Java based command-line tool that processes a range of numbers and applies specified divisor rules to produce a customized output. The app can print the results to the console or save them to a file.

## Features
- **Range Input**: The application prompts users to enter a start and end value for a range of numbers.
- **Divisor Rules**: Users can define custom divisor rules. For each divisor, the app prints a custom message for numbers divisible by that divisor.
- **Output Options**: The results can be printed to the console or saved to a file.
- **Flexible Configuration**: The file path for saving the results is configurable.

---

## Requirements
- Java 17 or higher
- Maven (for building and running the application)

---

## Installation

To install the application, build it using Maven:

mvn clean install

---

## Running the Application

There are two ways to run the application: **through the IDE** or **from the command line**.

### 1. Running from the IDE:
If you're using an IDE like IntelliJ IDEA or Eclipse:
- Simply run the `Application` class that contains the `main()` method.

### 2. Running from the Command Line:
You can also run the application using Maven from the command line:

mvn spring-boot:run

This will start the Spring Boot application and prompt you to enter input values.

---

## Usage

Once the application is running, you will be prompted to:

### 1. Enter the **range** of numbers:
You will be asked to input the **start** and **end** values for the range of numbers.

Example:

Enter start of range: 1 

Enter end of range: 10

### 2. Define **divisor rules**:
Enter a divisor and message (e.g., '2 foo'). Type 'done' to finish:

2 Foo

3 Bar

done

This means that:
- Numbers divisible by 2 will output "Foo"
- Numbers divisible by 3 will output "Bar"

You can add multiple rules, and the program will combine them when processing the numbers.

### 3. Choose where to print the results:
You will be asked if you want to save the results to a file or just print them to the console.

Example:

Do you want to save the results to a file? (yes/no)

If you choose "yes", the application will save the output into output.txt. If you choose "no", the results will be printed to the console.

Example output:

Results saved to file: output.txt

If you chose to print to the console, the output would look something like:

1 

Foo

Bar

Foo

5

FooBar

7

Foo

Bar

10

---

## Configuration

You can configure the file path for saving the results by editing the `application.properties`  file in the `src/main/resources` directory.

Example in `application.properties`:

output.filepath=path/to/your/output/file.txt

---

## Troubleshooting

- **Problem**: The application is not starting.
  - **Solution**: Ensure that you have Java 17 or higher installed and Maven is properly configured.

- **Problem**: Results are not saving to the file.
  - **Solution**: Verify that the `output.filepath` configuration is correct and that the program has write permissions to the specified directory.
