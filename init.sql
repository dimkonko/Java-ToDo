CREATE TABLE IF NOT EXISTS tasks (
	id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
	title CHAR(80) NOT NULL,
	isDone TINYINT(1) NOT NULL
);

INSERT INTO `tasks`(`title`, `isDone`) VALUES('Take a shower', 0);
INSERT INTO `tasks`(`title`, `isDone`) VALUES('Make a coffe', 1);