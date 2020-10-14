CREATE TABLE Grupos (id_grupo SERIAL PRIMARY KEY NOT NULL, nombre_grupo VARCHAR NOT NULL);

INSERT INTO Grupos (id_grupo, nombre_grupo) VALUES(1, 'GrupoAmigos');

CREATE TABLE Amigos (id SERIAL PRIMARY KEY NOT NULL, nombre VARCHAR(50) NOT NULL , id_grupo SERIAL NOT NULL, CONSTRAINT fk_friends FOREIGN KEY (id_grupo) REFERENCES Grupos(id_grupo));

INSERT INTO Amigos (id, nombre, id_grupo) VALUES(1, 'Francisco Buyo', 1);

CREATE TABLE Gastos (id SERIAL PRIMARY KEY NOT NULL, nombre VARCHAR(50), importe NUMERIC NOT NULL, descripcion VARCHAR, fecha TIMESTAMP);

INSERT INTO Gastos (id, nombre, importe, descripcion, fecha) VALUES(1, 'Francisco Buyo', 100, 'Cena', current_timestamp);