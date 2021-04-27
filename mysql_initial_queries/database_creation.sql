CREATE DATABASE Task_Manager;

USE Task_Manager;


CREATE TABLE task_status (
id INT(6) UNSIGNED AUTO_INCREMENT,
creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
name VARCHAR(30) NOT NULL,
description VARCHAR(50),
PRIMARY KEY (id),
CONSTRAINT task_status_unique_name UNIQUE (name)
);

INSERT INTO task_status 
VALUES(1,now(),now(),'Created', 'Status of a new task');

INSERT INTO task_status 
VALUES(2,now(),now(),'OnGoing', 'Someone is already working in this task');

INSERT INTO task_status 
VALUES(3,now(),now(),'Finished', 'This task has ended');


CREATE TABLE task (
id INT(6) UNSIGNED AUTO_INCREMENT,
creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
name VARCHAR(30) NOT NULL,
status_id INT(6) UNSIGNED NOT NULL,
description VARCHAR(50),
PRIMARY KEY (id),
FOREIGN KEY (status_id) REFERENCES task_status(id)
);

INSERT INTO task
VALUES (1,now(),now(),'Develop backend', 1, 'Create a functional JAVA application');

INSERT INTO task
VALUES (2,now(),now(),'Develop frontend', 1, 'Create a functional Angular application');

INSERT INTO task
VALUES (3,now(),now(),'Call Ainara', 1, 'Show Ainara the task manager');


