CREATE DATABASE bookstore CHARACTER SET utf8 COLLATE utf8_general_ci

CREATE TABLE bookstore.Student
(
  id INT NOT NULL,
  name VARCHAR(255),
  age INT NOT NULL,
  PRIMARY KEY ( id )
);