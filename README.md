# Student performance sorter

API that takes list of students with their grades and sorts them using sorting algorithm of your choice.

## How to run:

In your terminal, go to project's root directory and run this command: 

`./gradlew clean build && java -jar build/libs/student-performance-sorter-0.0.1-SNAPSHOT.jar`

This will build JAR application with provided gradle wrapper and run it locally on port 8080.

Go to http://localhost:8080 for Swagger documentation once the application is running.

Example .csv file with student data can be found [here](https://github.com/GVabal/student-performance-sorter/blob/main/src/main/resources/sample-data.csv).
