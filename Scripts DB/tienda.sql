BEGIN TRANSACTION;
CREATE TABLE `tienda` (
	`direccion`	TEXT,
	`nombre`	TEXT,
	`id`	INTEGER UNIQUE
);
COMMIT;
