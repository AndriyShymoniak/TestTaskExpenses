DROP DATABASE IF EXISTS expenses_test;
CREATE DATABASE expenses_test CHAR SET UTF8;
USE expenses_test;

CREATE TABLE expenses (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	date_purchased DATE, 
    amount DECIMAL,
    currency VARCHAR(3),
    product VARCHAR(50)
);

INSERT INTO expenses (date_purchased, amount, currency, product)
VALUES
("2021-03-01", 2300, 'UAH', 'Shoes'),
("2021-03-01", 400, 'UAH', 'T-shirt'),
("2021-03-01", 10, 'USD', 'Cinema ticket'),
("2021-03-01", 50, 'UAH', 'Beer'),
("2021-03-02", 900, 'UAH', 'Groceries'),
("2021-03-04", 130, 'UAH', 'Chocolates'),
("2021-03-05", 250, 'EUR', 'Printer'),
("2021-03-15", 699, 'USD', 'Samsung TV'),
("2021-03-20", 100, 'EUR', 'Utility payments'),
("2021-02-17", 30, 'EUR', 'Groceries'),
("2021-02-18", 15, 'USD', 'Book'),
("2021-02-19", 130, 'UAH', 'Chocolates'),
("2021-02-20", 35, 'EUR', 'Skipass'),
("2021-02-20", 15, 'EUR', 'Skii rental'),
("2021-02-20", 50, 'EUR', 'Cafe'),
("2021-02-20", 150, 'UAH', 'Wine'),
("2021-02-21", 150, 'EUR', 'Accommodation fee'),
("2021-03-21", 40, 'USD', 'Сar refueling'),
("2021-02-25", 40, 'USD', 'Groceries'),
("2021-02-26", 300, 'USD', 'Car service'),
("2021-02-27", 50, 'UAH', 'Jogurt'),
("2021-02-27", 13, 'EUR', 'Book')










