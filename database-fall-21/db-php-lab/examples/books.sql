CREATE DATABASE IF NOT EXISTS books;

USE books;

DROP TABLE IF EXISTS books;
CREATE TABLE books (
  isbn char(20) NOT NULL DEFAULT '',
  author char(50) DEFAULT NULL,
  title char(100) DEFAULT NULL,
  price float(4,2) DEFAULT NULL,
  PRIMARY KEY (isbn)
);


INSERT INTO books VALUES
('1491918667','Robin Nixon','Learning PHP, MySQL & JavaScript: With jQuery, CSS & HTML5',35.25),
('0672329166','Luke Welling','PHP and MySQL Web Development',32.67),
('0857522329','Paula Hawkins','The Girl on the Train',6.49),
('159413815X','Anthony Doerr','All the Light We Cannot See',13.60),
('0596517742','Douglas Crockford','JavaScript: The Good Parts',21.35),
('0321804333','Bill Phillips','Android Programming: The Big Nerd Ranch Guide',27.43),
('0062349546','Ann Patchett','State of Wonder',6.25),('0061340642','Ann Patchett','Run',11.82),
('0156006219','Ann Patchett','The Magician\\\'s Assistant',11.81);


DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  customerid int(10) unsigned NOT NULL AUTO_INCREMENT,
  name char(50) NOT NULL,
  address char(100) NOT NULL,
  city char(30) NOT NULL,
  PRIMARY KEY (customerid)
);


INSERT INTO customers VALUES
(1,'Cindy Howard','123 Main Street','Toronto'),
(2,'Ray Klump','One University Parkway','Romeoville'),
(3,'Steve Berger','1038 Canada Place','Vancouver'),
(44,'Barak Obama','1600 Pennsylvania Avenue','Washington DC');


DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  orderid int(10) unsigned NOT NULL AUTO_INCREMENT,
  customerid int(10) unsigned NOT NULL,
  amount float(6,2) DEFAULT NULL,
  date date NOT NULL,
  PRIMARY KEY (orderid)
);

INSERT INTO orders VALUES
(1,3,23.64,'2015-03-02'),
(2,1,27.43,'2015-02-15'),
(3,2,20.09,'2015-02-19'),
(4,3,82.29,'2015-03-01');


DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items (
  orderid int(10) unsigned NOT NULL,
  isbn char(13) NOT NULL,
  quantity tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (orderid,isbn)
);

INSERT INTO order_items VALUES
(1,'0061340642',2),
(2,'0321804333',1),
(3,'159413815X',1),
(3,'0857522329',1),
(4,'0321804333',3);


DROP TABLE IF EXISTS users;
CREATE TABLE users (
  name varchar(20) NOT NULL DEFAULT '',
  password varchar(123) DEFAULT NULL,
  PRIMARY KEY (name)
);

INSERT INTO users VALUES ('testuser','$5$rounds=10000$g46.eY0wJuobIXx2$Y9nPYjU3YnwHDPTVuj42XdopVZOyOFqc7fogWjxl6f8');

CREATE USER 'bookorama'@'localhost'  identified by 'cpsc33000';
GRANT SELECT, INSERT, UPDATE, DELETE on books.* TO 'bookorama'@'localhost';
