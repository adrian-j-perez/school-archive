-- add sql queries under the appropriate comment

-- ###Data Definition###

/*
    Name: Adrian Perez
    Class: Data Sys.
    Date: 2021/10/05
    About: making a database based of case study 6 the mail business 
*/

-- exercise 1 Write a SQL statement to create the database for the case study.
DROP DATABASE IF EXISTS mailorder;
CREATE DATABASE IF NOT EXISTS mailorder;
USE mailorder ;

-- exercise 2 add table and cols
CREATE TABLE IF NOT EXISTS Employees (
    employee_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    frist_name varchar(50),
    last_name varchar(50),
    zipcode varchar(5), 
    PRIMARY KEY (employee_id)    
);


CREATE TABLE IF NOT EXISTS Orders (
    order_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    employee_id SMALLINT UNSIGNED ,
    customer_id SMALLINT UNSIGNED ,
    today_date DATETIME,
    expected_date_ship DATE,
    date_arrived DATE,
    PRIMARY KEY (order_id),
    CONSTRAINT employee_id_fk FOREIGN KEY (employee_id)
    REFERENCES Employees(employee_id)
);


-- exercise 3 modify one of the tables
ALTER TABLE Employees
MODIFY COLUMN frist_name varchar(40) NOT NULL;


-- exercise 4 Insert at least two rows in each of the tables.
INSERT INTO Employees (employee_id, frist_name, last_name, zipcode)
VALUES (1, "Adrian", "Perez", "60433");

INSERT INTO Employees(frist_name, last_name, zipcode)
VALUES( "John", "Doe", "60433");

INSERT INTO Orders(customer_id, expected_date_ship, date_arrived)
VALUES (2, "2021/10/3", "2021/10/4");

INSERT INTO Orders(expected_date_ship, date_arrived)
VALUES ("2021/10/6", "2021/10/7");


-- exercise 5 Write a SQL query to update one row in one of the tables.
UPDATE Orders
SET customer_id = 99, date_arrived = "2021/10/11"
WHERE order_id = 2;
