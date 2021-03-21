# Personal expenses management application
This program, provides basic functions for users to track how much money have they spent.

You can easily install and use it, following this step by step program execution guide.

## Installation
1. Make sure that you have Java 11 JDK, any Java Environment, Postman and MySQL Workbench installed.
2. Clone this project.
3. Find "expenses_db.sql" file in "\src\main\resources" folder, open it as SQL script and run it to create database and set initial values.
4. You should also change a few fields in Java application properties located in "test_app\src\main\resources\application.properties":

- spring.datasource.username = (your MySQL Workbench instance username)
- spring.datasource.password = (your MySQL Workbench instance password)
5. Now you can run Java project. The main class is located in "src\main\java\com\shymoniak\expenses\ExpensesApplication.java".
6. You can also use my json requests collection, after importing it from "src\main\resources\expenses.postman_collection.json" to test the application.

## Functions
1. Thi application has endpoint to add new Expenses (all fields are required);
2. Endpoint to show the list of all expenses grouped and sorted by date;
3. Endpoint to remove all expenses for specified date;
4. Endpoint to show total amount of money spent on buying (in currency specified by user)
