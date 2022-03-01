USE MITIENDA0221;
describe restaurante;
INSERT INTO restaurante (NOMBRE,DIRECCION,CIUDAD)
VALUES ( "MUY_SEDE_NORTE", "CHICO_NORTE", "BOGOTA");
INSERT INTO restaurante (NOMBRE,DIRECCION,CIUDAD)
VALUES ( "MUY_SEDE_CENTRO", "CENTRO", "BOGOTA");
describe tipo_pregunta;
INSERT INTO tipo_pregunta (tipo_pregunta)
VALUES ( "ABIERTA" );
INSERT INTO tipo_pregunta (tipo_pregunta)
VALUES ( "SELEC_MULTIPLE" );
describe encuesta;
INSERT INTO encuesta (nombre_encuesta, id_restaurante)
VALUES ( "Percepción de atención al cliente", 1);

INSERT INTO encuesta (nombre_encuesta, id_restaurante)
VALUES ( "Percepción de platillos fuertes", 1);
describe pregunta;
INSERT INTO pregunta (TEXTO_PREGUNTA,ID_ENCUESTA,ID_TIPO)
VALUES ( "¿Considera mala la atención al cliente? puede indicar su opinión?",1,1);

INSERT INTO pregunta (TEXTO_PREGUNTA,ID_ENCUESTA,ID_TIPO)
VALUES ( "Que aspectos mejoraría con respecto a las instalaciones de la sede",1,1);

INSERT INTO pregunta (TEXTO_PREGUNTA,ID_ENCUESTA,ID_TIPO)
VALUES ( "Seleccione la opción que identifica, mejor la atención al cliente de la sede",1,2);
describe OPCION_MULTIPLE;
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("a. Excelente","a",3);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("b. No me gusta","b",3);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("c. Buena","c",3);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("d. No agradable","d",3);

INSERT INTO pregunta (TEXTO_PREGUNTA,ID_ENCUESTA,ID_TIPO)
VALUES ( "¿Como le parecio la comida",1,2);
describe OPCION_MULTIPLE;
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("a. Excelente","a",4);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("b. No me gusto","b",4);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("c. Buena","c",4);
INSERT INTO OPCION_MULTIPLE(TEXTO_OPCION_MUL,LETRA_OPCION_MULTIPLE,ID_PREGUNTA)
VALUES("d. Bastante Desagradable","d",4);

DESCRIBE cliente;
INSERT INTO CLIENTE (NOMBRE_CLIENTE)
VALUES("Monica Castellanos");

DESCRIBE respuesta_abierta;
INSERT INTO respuesta_abierta (TEXTO_RESPUESTA,ID_PREGUNTA,ID_CLIENTE)
VALUES("No, en general me atendieron muy bien",1,1);

INSERT INTO respuesta_multiple (ID_OPCION_MULTIPLE,ID_PREGUNTA,ID_CLIENTE)
VALUES(4,3,1);

INSERT INTO pregunta (TEXTO_PREGUNTA,ID_ENCUESTA,ID_TIPO)
VALUES ( "¿El platillo Fuerte pasta a la Carbonara que tal le parece?",3,1);

SELECT * FROM ENCUESTA;

SELECT * FROM CLIENTE;
select * from opcion_multiple;
select * from pregunta;
SELECT C.NOMBRE_CLIENTE,P.TEXTO_PREGUNTA, OM.TEXTO_OPCION_MUL, OM.LETRA_OPCION_MULTIPLE , p.ID_PREGUNTA, C.ID_CLIENTE, OM.ID_OPCION_MULTIPLE
 FROM respuesta_multiple AS M
INNER JOIN cliente AS C ON M.ID_CLIENTE = C.ID_CLIENTE
INNER JOIN pregunta AS P ON M.ID_PREGUNTA = P.ID_PREGUNTA
INNER JOIN opcion_multiple AS OM ON M.ID_OPCION_MULTIPLE = OM.ID_OPCION_MULTIPLE;

SELECT C.NOMBRE_CLIENTE, p.ID_PREGUNTA, P.TEXTO_PREGUNTA, M.TEXTO_RESPUESTA
 FROM respuesta_abierta AS M
INNER JOIN cliente AS C ON M.ID_CLIENTE = C.ID_CLIENTE
INNER JOIN pregunta AS P ON M.ID_PREGUNTA = P.ID_PREGUNTA;

select * from encuesta;
select e.ID_ENCUESTA, e.NOMBRE_ENCUESTA, pr.TEXTO_PREGUNTA, tp.TIPO_PREGUNTA, op.TEXTO_OPCION_MUL
 from encuesta e inner join pregunta pr on pr.ID_ENCUESTA = e.ID_ENCUESTA 
inner join tipo_pregunta tp on tp.ID_TIPO = pr.ID_TIPO
left join opcion_multiple op on pr.ID_PREGUNTA = op.ID_PREGUNTA;


















