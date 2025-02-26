/*
Name: Adrian Perez
Class: Data Sys.
Date: 2021/12/13
About: This database is for the final project
*/

-- source C:/xampp/htdocs/project/db-final-project-7pereza/devicedb.sql

DROP DATABASE IF EXISTS devicedb;
CREATE DATABASE IF NOT EXISTS devicedb;
USE devicedb ;

-- making tables
CREATE TABLE IF NOT EXISTS users (
    users_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name varchar(50),
    last_name varchar(50), 
    PRIMARY KEY (users_id)
);

CREATE TABLE IF NOt EXISTS devices (
    device_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    device_name varchar(30),
    year_made varchar(4),
    model varchar(50),
    category varchar(35),
    descrip varchar(150),
    PRIMARY KEY (device_id)    
);

--this is the table for the m to m 

CREATE TABLE IF NOT EXISTS uses(
    user SMALLINT UNSIGNED NOT NULL,
    device SMALLINT UNSIGNED NOT NULL,
    
    CONSTRAINT user_id_fk FOREIGN KEY (user) 
    REFERENCES users(users_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    CONSTRAINT device_id_fk FOREIGN KEY (device) 
    REFERENCES devices(device_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    PRIMARY KEY (user, device)    
);


-- making a index on device category
CREATE INDEX fname_index ON users(first_name);
CREATE INDEX device_name_index on devices(device_name);


-- inserting sample data
INSERT INTO users(users_id, first_name, last_name)
VALUES (1, "Adrian", "Perez");
INSERT INTO users(users_id, first_name, last_name)
VALUES (2,"mom", "Perez");
INSERT INTO users(users_id, first_name, last_name)
VALUES (3,"dad", "Perez");
INSERT INTO users(users_id, first_name, last_name)
VALUES (4,"sis", "Perez");


INSERT INTO devices(device_id, device_name, year_made, model, category, descrip)
VALUES (1,"living room tv","2020","samsung oled dot","TV","50 inche flat screen tv to be shared with the family");
INSERT INTO devices(device_id, device_name, year_made, model, category, descrip)
VALUES (2,"adrian phone","2019","samsung s21","phone","this is my daily driver");
INSERT INTO devices(device_id, device_name, year_made, model, category, descrip)
VALUES (3,"mom phone","2021","iphone 13x max","phone","her main phone");
INSERT INTO devices(device_id, device_name, year_made, model, category, descrip)
VALUES (4,"dad phone","2018","lg","phone","old reliable");
INSERT INTO devices(device_id, device_name, year_made, model, category, descrip)
VALUES (5,"sister phone","2021","iphone max","phone","litte sisters new iphone. wow");

 
INSERT INTO uses(user, device)
VALUES (1, 1);
INSERT INTO uses(user, device)
VALUES (2, 1);
INSERT INTO uses(user, device)
VALUES (3, 1);
INSERT INTO uses(user, device)
VALUES (4, 1);
INSERT INTO uses(user, device)
VALUES (1, 2);
INSERT INTO uses(user, device)
VALUES (2, 3);
INSERT INTO uses(user, device)
VALUES (4, 5);
INSERT INTO uses(user, device)
VALUES (2, 5);
INSERT INTO uses(user, device)
VALUES (3, 5);


-- adding sql users
DROP USER IF EXISTS 'appuser1'@'localhost';
CREATE USER 'appuser1'@'localhost' IDENTIFIED BY 'password1';

-- giving access to db -- might not need update data for now
GRANT SELECT, INSERT, UPDATE
ON devicedb.* TO 'appuser1'@'localhost' ;

