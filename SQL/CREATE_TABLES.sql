DROP TABLE IF EXISTS `RESPUESTA_ABIERTA`;
DROP TABLE IF EXISTS `RESPUESTA_MULTIPLE`;
DROP TABLE IF EXISTS `CLIENTE`;
DROP TABLE IF EXISTS `OPCION_MULTIPLE`;
DROP TABLE IF EXISTS `PREGUNTA`;
DROP TABLE IF EXISTS `TIPO_PREGUNTA`;
DROP TABLE IF EXISTS `ENCUESTA`;
DROP TABLE IF EXISTS `RESTAURANTE`;

CREATE TABLE RESTAURANTE (
ID_RESTAURANTE int(10) NOT NULL auto_increment,
NOMBRE 	VARCHAR(200) NOT NULL,
DIRECCION VARCHAR(150) NOT NULL,
CIUDAD VARCHAR(30) NOT NULL,
PRIMARY KEY (ID_RESTAURANTE)
);


CREATE TABLE ENCUESTA (
ID_ENCUESTA int(10)  NOT NULL auto_increment,
NOMBRE_ENCUESTA VARCHAR(300) NOT NULL,
ID_RESTAURANTE int(10),
PRIMARY KEY (ID_ENCUESTA),
FOREIGN KEY (ID_RESTAURANTE) REFERENCES RESTAURANTE(ID_RESTAURANTE)
);


CREATE TABLE TIPO_PREGUNTA (
ID_TIPO int(10)  NOT NULL auto_increment,
TIPO_PREGUNTA VARCHAR(30) NOT NULL,
PRIMARY KEY (ID_TIPO)
);


CREATE TABLE PREGUNTA (
ID_PREGUNTA int(10)  NOT NULL auto_increment,
TEXTO_PREGUNTA VARCHAR(500) NOT NULL,
ID_ENCUESTA int(10),
ID_TIPO INT (10),
PRIMARY KEY (ID_PREGUNTA),
FOREIGN KEY (ID_ENCUESTA) REFERENCES ENCUESTA(ID_ENCUESTA),
FOREIGN KEY (ID_TIPO) REFERENCES TIPO_PREGUNTA(ID_TIPO)
);

CREATE TABLE OPCION_MULTIPLE (
ID_OPCION_MULTIPLE int(10) NOT NULL auto_increment,
TEXTO_OPCION_MUL VARCHAR(500) NOT NULL,
LETRA_OPCION_MULTIPLE char NOT NULL,
ID_PREGUNTA int(10),
PRIMARY KEY (ID_OPCION_MULTIPLE),
FOREIGN KEY (ID_PREGUNTA) REFERENCES PREGUNTA(ID_PREGUNTA)
);

CREATE TABLE CLIENTE (
ID_CLIENTE int(10) NOT NULL auto_increment,
NOMBRE_CLIENTE VARCHAR(100) NOT NULL,
PRIMARY KEY (ID_CLIENTE)
);
CREATE TABLE RESPUESTA_ABIERTA (
ID_RESPUESTA_AB int(10) NOT NULL auto_increment,
TEXTO_RESPUESTA VARCHAR(500) NOT NULL,
ID_PREGUNTA INT (10),
ID_CLIENTE INT (10),
PRIMARY KEY (ID_RESPUESTA_AB),
FOREIGN KEY (ID_PREGUNTA) REFERENCES PREGUNTA(ID_PREGUNTA),
FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)
);

CREATE TABLE RESPUESTA_MULTIPLE (
ID_RESPUESTA_MULT INT  NOT NULL auto_increment,
ID_OPCION_MULTIPLE int(10),
ID_PREGUNTA  int(10),
ID_CLIENTE INT (10),
PRIMARY KEY(ID_RESPUESTA_MULT),
FOREIGN KEY (ID_OPCION_MULTIPLE) REFERENCES OPCION_MULTIPLE(ID_OPCION_MULTIPLE),
FOREIGN KEY (ID_PREGUNTA) REFERENCES PREGUNTA(ID_PREGUNTA),
FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)
);

ALTER TABLE CLIENTE ADD COLUMN CORREO VARCHAR(60) NOT NULL UNIQUE;
