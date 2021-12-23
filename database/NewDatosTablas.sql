do $$
declare 
id1 int;
id2 int;
id3 int;
id4 int;
id5 int;
id6 int;
id7 int;
id8 int;
id9 int;
id10 int;
id11 int;
<<<<<<< HEAD
id12 int;
id13 int;
id14 int;
id15 int;
=======
idOcup1 int;
>>>>>>> d1d1b9edd8ebb29cdf56269cd6aad38f9b3aaf61

begin
--Datos Pos Iva
insert into posiva (ident, tipo) values (1, 'consumidor final');
insert into posiva (ident, tipo) values (2, 'monotributista');
insert into posiva (ident, tipo) values (3, 'responsable inscripto');

--unidades
insert into unidades (id_unidades, fechaconsumo, unidades) values (019, '21/11/2021', 2);
insert into unidades (id_unidades, fechaconsumo, unidades) values (029, '21/11/2021', 5);
insert into unidades (id_unidades, fechaconsumo, unidades) values (039, '12/11/2021', 4);
insert into unidades (id_unidades, fechaconsumo, unidades) values (049, '02/12/2021', 4);
insert into unidades (id_unidades, fechaconsumo, unidades) values (059, '26/11/2021', 5);
insert into unidades (id_unidades, fechaconsumo, unidades) values (069, '01/12/2021', 6);
insert into unidades (id_unidades, fechaconsumo, unidades) values (079, '04/12/2021', 7);
insert into unidades (id_unidades, fechaconsumo, unidades) values (089, '14/11/2021', 8);
insert into unidades (id_unidades, fechaconsumo, unidades) values (099, '20/11/2021', 9);
insert into unidades (id_unidades, fechaconsumo, unidades) values (0119, '01/12/2021', 3);

--Datos Pais
insert into pais (id_pais, nombre, nacionalidad) values (54, 'Argentina', 'Argentina');
insert into pais (id_pais, nombre, nacionalidad) values (55, 'Colombia', 'Colombia');
insert into pais (id_pais, nombre, nacionalidad) values (56, 'Vietnam', 'Vietnam');
insert into pais (id_pais, nombre, nacionalidad) values (57, 'Ecuador', 'Ecuador');
insert into pais (id_pais, nombre, nacionalidad) values (58, 'Brasil', 'Brasil');
insert into pais (id_pais, nombre, nacionalidad) values (59, 'Chile', 'Chile');
insert into pais (id_pais, nombre, nacionalidad) values (60, 'Uruguay', 'Uruguay');
insert into pais (id_pais, nombre, nacionalidad) values (61, 'Cuba', 'Cuba');
insert into pais (id_pais, nombre, nacionalidad) values (62, 'Estados Unidos', 'Estados Unidos');
insert into pais (id_pais, nombre, nacionalidad) values (63, 'Mexico', 'Mexico');

--Datos Provincia
insert into provincia (nombre, codigoProvincia, Pais) values ('Santa fe', 1, 54);
insert into provincia (nombre, codigoProvincia, Pais) values ('Entre Rios', 2, 54);
insert into provincia (nombre, codigoProvincia, Pais) values ('Texas', 3, 62);
insert into provincia (nombre, codigoProvincia, Pais) values ('Florida', 4, 62);
insert into provincia (nombre, codigoProvincia, Pais) values ('Sao Paulo', 5, 58);
insert into provincia (nombre, codigoProvincia, Pais) values ('Bahia', 6, 58);
insert into provincia (nombre, codigoProvincia, Pais) values ('Ciudad de Mexico', 7, 63);
insert into provincia (nombre, codigoProvincia, Pais) values ('Antofagasta', 8, 59);
insert into provincia (nombre, codigoProvincia, Pais) values ('Valparaiso', 9, 59);
insert into provincia (nombre, codigoProvincia, Pais) values ('Maldonado', 10, 60);

--Datos Localidad
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Santa fe ciudad', '3000', 1, '1');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Rosario', '3001', 2, '1');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Parana', '2016', 3, '2');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Maria Grande', '2000', 4, '2');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Orlando', '34383', 5, '4');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Houston', '9645', 6, '3');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Moscu', '586', 7, '7');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Rouen', '123', 8, '5');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Castilho', '3075', 9, '5');
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Valparaiso', '9345', 10, '9');

--Datos IDType
insert into IDType (tipoDeID) values ('DNI');
insert into IDType (tipoDeID) values ('LC');
insert into IDType (tipoDeID) values ('Otro');
insert into IDType (tipoDeID) values ('LE');
insert into IDType (tipoDeID) values ('Pasaporte');

--Seccion consumo
insert into seccionconsumo (seccion) values ('Higene');
insert into seccionconsumo (seccion) values ('Bebida sin alcohol');
insert into seccionconsumo (seccion) values ('Belleza');
insert into seccionconsumo (seccion) values ('Comida');
insert into seccionconsumo (seccion) values ('Farmacia');
insert into seccionconsumo (seccion) values ('Bebida con alcohol');

--Datos TipoHabitacion
insert into tipohabitacion (id, tipo, costo) values (1, 'Individual Estándar', 4200);
insert into tipohabitacion (id, tipo, costo) values (2, 'Doble Estándar', 6240);
insert into tipohabitacion (id, tipo, costo) values (3, 'Doble Superior', 7308);
insert into tipohabitacion (id, tipo, costo) values (4, 'Superior Family Plan', 10500);
insert into tipohabitacion (id, tipo, costo) values (5, 'Suite Doble', 12600);

--Item consumo
insert into itemconsumo (id_item, categoria, nombre, costo) values (0199, 'Higene', 'PapelHigenico', '52.95');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0299, 'Bebida sin alcohol', 'Sprite', '7.57');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0399, 'Belleza', 'Sombra para Ojos', '92.40');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0499, 'Bebida sin alcohol', 'Cocacola', '8.36');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0599, 'Comida', 'Hamburguesa con papas', '77.88');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0699, 'Comida', 'Hamburguesa con papas', '29.36');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0799, 'Higene', 'Desodorante', '91.67');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0899, 'Farmacia', 'Ibuprofeno', '61.10');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0999, 'Farmacia', 'Paracetamol', '52.54');
insert into itemconsumo (id_item, categoria, nombre, costo) values (01099, 'Bebida con alcohol', 'Vino Blanco', '35.41');

--Datos Habitacion
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 7, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 8, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 9, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 10, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 7, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 8, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 9, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 10, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 7, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 8, 3, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 7, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 8, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 9, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 10, 4, 4, 5, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 21, 4, 5, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 22, 4, 5, 2, 0);

--Datos Ocupacion
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (1, 6, 3, id14, '23/11/2021', '05/12/2021');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (2, 4, 2, id5, '22/11/2021', '06/12/2021');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (3, 8, 1, id10, '23/11/2021', '01/12/2021');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (4, 5, 3, id6, '09/11/2021', '21/11/2021');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (5, 2, 1, id2, '20/11/2021', '02/12/2021');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (6, 10, 1, id8, '16/11/2021', '19/11/2021');

--Periodo estadia
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00001, 1, '23/11/2021', '26/11/2021', '600.86');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00002, 2, '22/11/2021', '29/11/2021', '6758.76');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00003, 3, '23/11/2021', '24/11/2021', '8523.47');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00004, 6, '16/11/2021', '06/11/2021', '2145.57');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00005, 4, '09/11/2021', '17/11/2021', '7609.79');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00006, 4, '17/11/2021', '21/11/2021', '5541.41');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00007, 3, '24/11/2021', '01/12/2021', '9025.69');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00008, 5, '20/11/2021', '02/12/2021', '6675.94');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00009, 1, '26/11/2021', '05/12/2021', '4943.51');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (000010,6, '06/11/2021', '19/11/2021', '1221.74');

--Consumo servicio
insert into consumoservicios (id_consumo, ocupacion, costototal) values (01, 1, '3724.02');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (02, 2, '1216.90');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (03, 1, '4066.48');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (04, 3, '443.56');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (05, 3, '4937.15');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (06, 3, '4785.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (07, 1, '5041.43');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (08, 2, '8699.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (09, 2, '9159.50');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (011, 2, '9969.75');

--Consumo unidades
insert into consumounidades (id_unidades, id_consumo, id_item) values (019, 01, 0199);
insert into consumounidades (id_unidades, id_consumo, id_item) values (029, 02, 0299);
insert into consumounidades (id_unidades, id_consumo, id_item) values (039, 03, 0399);
insert into consumounidades (id_unidades, id_consumo, id_item) values (049, 04, 0499);
insert into consumounidades (id_unidades, id_consumo, id_item) values (059, 05, 0599);
insert into consumounidades (id_unidades, id_consumo, id_item) values (069, 06, 0699);
insert into consumounidades (id_unidades, id_consumo, id_item) values (079, 07, 0799);
insert into consumounidades (id_unidades, id_consumo, id_item) values (089,08, 0899);
insert into consumounidades (id_unidades, id_consumo, id_item) values (099, 09, 0999);
insert into consumounidades (id_unidades, id_consumo, id_item) values (0119, 011, 01099);

--Datos persona
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3507913321', 'dglasbey0@whitehouse.gov', null, 'Veith', '78179', 1, 4) returning idpersona into id1;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9841503697', 'adunne1@hexun.com', '9689632486', 'Bartillon', '3', 1, 2)returning idpersona into id2;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4491706689', 'eseddon2@163.com', null, 'Lake View', '7881', 3, 9)returning idpersona into id3;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4779066867', 'mhallut3@timesonline.co.uk', '123456789', 'Marcy', '3515', 2, 9)returning idpersona into id4;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '8935833177', 'hshoorbrooke4@who.int', null, 'Cherokee', '5443', 2, 10)returning idpersona into id5;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3323145720', 'vjesper5@imdb.com', '9632574129', 'Sloan', '4', 1, 10)returning idpersona into id6;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '2258463920', 'chelian6@arstechnica.com', null, 'Armistice', '84', 3, 2)returning idpersona into id7;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9037432352', 'gshakshaft7@photobucket.com', '20456932159', 'Susan', '5514', 3, 7)returning idpersona into id8;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '1816528986', 'rfriedman8@imdb.com', null, 'Buhler', '624', 2, 3)returning idpersona into id9;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9630048949', 'lthuillier9@wisc.edu', null, 'Sherman', '1129', 3, 8)returning idpersona into id10;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3426953214', 'mcdonalds@gmail.com', '123456789', 'Los Cardenales', '123', 3, 8)returning idpersona into id11;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4565496621', 'microsft@gmail.com', null, 'Las Hienas', '543', 3, 8)returning idpersona into id12;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3691158873', 'google@riotgames.com', null, '9 de julio', '2855', 1, 8)returning idpersona into id13;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '7992139873', 'facebook@riotgames.com', '20496392159', 'Blvd Galvez', '3432', 2, 7)returning idpersona into id14;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4963179873', 'twitter@riotgames.com', null, 'San Martin', '1369', 3, 4)returning idpersona into id15;

insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '6435325467', 'aertgfdryte@hgf.com', '24575464774', '1st Street', '5431', 3, 8)returning idpersona into id11;

--Datos Pasajero
<<<<<<< HEAD
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id1, 'Kandace', 'Maffioletti', '14567782', 'Pasaporte', 'Administrative Officer', '03/11/1949', 60);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id2, 'Ginni', 'Breach', '40613405', 'LE', 'Marketing Manager', '08/02/2000', 60);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id3, 'Rab', 'Debill', '40744247', 'DNI', 'Senior Quality Engineer', '05/04/2000', 63);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id4, 'Alberik', 'Alenichev', '25800567', 'LC', 'Executive Secretary', '30/04/1975', 62);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id5, 'Karisa', 'Kell', '41050258', 'LC', 'Web Designer I', '11/08/1998', 60);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id6, 'Gustaf', 'Kunkel', '13688843', 'Otro', 'Operator', '08/09/1943', 63);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id7, 'Clifford', 'Anglim', '30439748', 'Otro', 'Physical Therapy Assistant', '08/08/1985', 61);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id8, 'Margaret', 'Stetlye', '36798328', 'Otro', 'Professor', '13/07/1990', 57);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id9, 'Avrom', 'Ebdon', '40914032', 'LE', 'Senior Sales Associate', '21/12/1945', 55);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id10, 'Orly', 'Hancox', '39480933', 'Pasaporte', 'Senior Financial Analyst', '03/03/1971', 54);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id11, 'Max', 'Ewbanks', '14385954', 'Otro', 'Analyst Programmer', '26/02/1979', 59);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id12, 'Eve', 'Olivera', '33503825', 'Otro', 'Data Coordiator', '17/11/1950', 55);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id13, 'Shanta', 'Scotland', '24770915', 'Pasaporte', 'Teacher', '28/06/1955', 63);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id14, 'Rubie', 'Verralls', '25240660', 'Pasaporte', 'Actuary', '12/10/1963', 61);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id15, 'Modestine', 'Pryce', '22688660', 'DNI', 'Tax Accountant', '12/12/1983', 61);

--Datos Persona Juridica
insert into persona_juridica (idpersona, domiciliofiscal, razonsocial) values (id11, 'Los Cardenales 123', 'McDonalds');
insert into persona_juridica (idpersona, domiciliofiscal, razonsocial) values (id12, 'Las Hienas 543', 'Microsoft');
insert into persona_juridica (idpersona, domiciliofiscal, razonsocial) values (id13, 'Olympic Blvd 12333', 'Riot Games');

--Datos Reserva
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (1, 1, 1, 'Bondy', 'Rylatt', '3808721502', '2021/09/11', '2021/09/20');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (2, 1, 1, 'Franny', 'Gumme', '9093832379', '2021/09/21', '2021/09/30');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (3, 1, 1, 'Richy', 'Wieprecht', '3533356477', '2021/09/01', '2021/09/10');
=======
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id1, 'Bondy', 'Rylatt', '3808721502', 'LC', 'Office Assistant IV', '2000-09-11', 1);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id2, 'Luce', 'Theml', '9936502027', 'LE', 'Chief Design Engineer', '2000-02-13', 1);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id3, 'Ricki', 'Lapworth', '7519091015', 'Otro', 'Food Chemist', '2000-06-21', 4);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id4, 'Franny', 'Gumme', '9093832379', 'LC', 'Junior Executive', '2000-08-10', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id5, 'Renell', 'St. Quentin', '6694351917', 'DNI', 'Nuclear Power Engineer', '2010-01-19', 7);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id6, 'Colene', 'Rogan', '3828106110', 'Otro', 'Assistant Professor', '2009-02-14', 7);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id7, 'Richy', 'Wieprecht', '3533356477', 'LE', 'Geological Engineer', '2002-06-25', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id8, 'Ewan', 'Latek', '0084924403', 'LC', 'Office Assistant I', '2000-04-03', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id9, 'Shandee', 'Record', '3017832678', 'DNI', 'Biostatistician III', '2000-02-23', 8);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id10, 'Artus', 'Munn', '2688732048', 'DNI', 'Clinical Specialist', '1999-09-20', 7);

--Datos TipoHabitacion
insert into tipohabitacion (id, tipo, costo) values (1, 'Individual Estandar', 99.99);
insert into tipohabitacion (id, tipo, costo) values (2, 'Doble Estandar', 199.99);
insert into tipohabitacion (id, tipo, costo) values (3, 'Doble Superior', 299.99);
insert into tipohabitacion (id, tipo, costo) values (4, 'Superior Family Plan', 399.99);
insert into tipohabitacion (id, tipo, costo) values (5, 'Suite Doble', 999.99);

--Datos Habitacion
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 1, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 1, 4, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 1, 5, 2, 0);

insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 2, 1, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 4, 2, 3, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 5, 2, 4, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 6, 2, 5, 2, 0);

--Datos Seccion_Consumo
insert into seccion_consumo (id_categoria, tipo) values (1, 'Lavado y Planchado');
insert into seccion_consumo (id_categoria, tipo) values (2, 'Sauna');
insert into seccion_consumo (id_categoria, tipo) values (3, 'Bar');

--Datos Reserva
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (1, 1, 1, 'Bondy', 'Rylatt', '3808721502', '2021-09-11', '2021-09-20');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (2, 1, 1, 'Franny', 'Gumme', '9093832379', '2021-09-21', '2021-09-30');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (3, 1, 1, 'Richy', 'Wieprecht', '3533356477', '2021-09-01', '2021-09-10');

--Datos Ocupacion
insert into ocupacion ( numero, piso, responsable, checkin, checkout) values ( 1, 2, id1, '2021-09-21', '2021-09-30');
insert into ocupacion ( numero, piso, responsable, checkin, checkout) values ( 1, 2, id2, '2021-09-01', '2021-09-12');
insert into ocupacion ( numero, piso, responsable, checkin, checkout) values ( 1, 2, id3, '2021-09-13', '2021-09-15');
insert into ocupacion ( numero, piso, responsable, checkin, checkout) values ( 1, 2, id3, '2021-12-13', '2021-12-30') returning id_ocupacion into idOcup1;
>>>>>>> d1d1b9edd8ebb29cdf56269cd6aad38f9b3aaf61

--Datos Fuera De Servicio
insert into fueradeservicio (id_fueradeservicio, numero, piso, desde, hasta) values (1, 2, 1,'2021/09/11', '2021/09/25');

--Nota de credito
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (177, 3, '12/11/2021', 374.79, 971.47, '9093832379');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (277, 4, '01/11/2021', 986.39, 665.4, '3533356477');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (377, 6, '09/11/2021', 23.24, 271.56, '0084924403');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (477, 8, '22/11/2021', 551.37, 673.71, '9936502027');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (577, 10, '06/11/2021', 810.78, 464.44, '3828106110');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (677, 3, '17/11/2021', 233.9, 551.26, '7519091015');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (777, 2, '25/11/2021', 41.66, 169.14, '3533356477');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (877, 4, '16/11/2021', 670.19, 909.86, '7519091015');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (977, 3, '08/11/2021', 381.02, 484.23, '3017832678');
insert into notadecredito (id_nota, ResponsablePago, FechaFactura, ImpNeto, IVA, DNICUIT) values (1077, 1, '18/11/2021', 360.52, 541.88, '9936502027');

--Responsable de pago
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (188, 10, '8268408731', '7', '9881593437');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (288, 6, '7615091365', '45', '9294515057');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (388, 2, '4606590734', '24', '7865390980');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (488, 5, '4406968032', '18', '5776171919');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (588, 5, '9208176436', '5674', '6235825609');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (688, 9, '4428602197', '35', '8953264011');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (788, 5, '1020257814', '735', '1737370647');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (888, 9, '4010710020', '65757', '2135008904');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (988, 6, '8064642833', '4441', '9277477022');
insert into RespDePago (id_respDePago, id_Persona, DNICUIT, NumDireccion, Telefono) values (1088, 7, '4962572946', '45', '3693238755');

--Factura
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (10990, 00001, 177, 188, '1/2/2021', '3338.06');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (20990, 00002, 277, 288, '22/11/2021', '2218.04');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (30990, 00003, 377, 388, '13/12/2021', '8924.07');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (40990, 00004, 477, 488, '19/11/2021', '9581.97');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (50990, 00005, 577, 588, '16/11/2021', '3572.90');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (60990, 00006, 677, 688, '27/11/2021', '2170.36');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (70990, 00007, 777, 788, '28/11/2021', '1074.74');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (80990, 00008, 877, 888, '20/12/2021', '1907.52');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (90990, 00009, 977, 988, '21/11/2021', '1971.98');
insert into factura (id_factura, id_estadia, nota_credito, id_respdepago, fecha, monto_total) values (100990, 000010, 1077, 1088, '15/11/2021', '5441.09');

--Pago 
insert into pago (id_pago, id_factura, monto) values (10770, 10990, '269.79');
insert into pago (id_pago, id_factura, monto) values (20770, 20990, '790.70');
insert into pago (id_pago, id_factura, monto) values (30770, 30990, '954.36');
insert into pago (id_pago, id_factura, monto) values (40770, 40990, '748.78');
insert into pago (id_pago, id_factura, monto) values (50770, 50990, '143.90');
insert into pago (id_pago, id_factura, monto) values (60770, 60990, '157.95');
insert into pago (id_pago, id_factura, monto) values (70770, 70990, '189.41');
insert into pago (id_pago, id_factura, monto) values (80770, 80990, '176.24');
insert into pago (id_pago, id_factura, monto) values (90770, 90990, '93.82');
insert into pago (id_pago, id_factura, monto) values (100770, 100990, '641.14');

--Detalle Factura
insert into detallesfactura (id_factura, id_detalle, costototal) values (10990, 111222, '265.27');
insert into detallesfactura (id_factura, id_detalle, costototal) values (20990, 222333, '649.85');
insert into detallesfactura (id_factura, id_detalle, costototal) values (30990, 333444, '381.23');
insert into detallesfactura (id_factura, id_detalle, costototal) values (40990, 444555, '843.96');
insert into detallesfactura (id_factura, id_detalle, costototal) values (50990, 555666, '575.21');
insert into detallesfactura (id_factura, id_detalle, costototal) values (60990, 666777, '941.52');
insert into detallesfactura (id_factura, id_detalle, costototal) values (70990, 777888, '506.75');
insert into detallesfactura (id_factura, id_detalle, costototal) values (80990, 888999, '486.14');
insert into detallesfactura (id_factura, id_detalle, costototal) values (90990, 9991010, '346.96');
insert into detallesfactura (id_factura, id_detalle, costototal) values (100990, 10101111, '3.44');

--Detalle Unidad
insert into detalleunidades (id_unidades, id_detalle, id_item) values (019, 111222, 0199);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (029, 222333, 0299);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (039, 333444, 0399);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (049, 444555, 0499);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (059, 555666, 0599);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (069, 666777, 0699);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (079, 777888, 0799);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (089, 888999, 0899);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (099, 9991010, 0999);
insert into detalleunidades (id_unidades, id_detalle, id_item) values (0119, 10101111, 01099);

--Datos Item Consumo
insert into item_consumo(id_item, id_categoria, nombre, costo) values(1,1,'planchado pantalon',100);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (2,1,'lavado camisa', 150);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (3,1,'lavado remera', 100);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (4,1,'planchado camisa', 150);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (5,1,'lavado ropa interior', 100);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (6,2,'masaje',1500);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (7,2,'sauna finlandes',1000);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (8,2,'manicuria',900);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (9,2,'fangoterapia',2500);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (10,2,'reflexologia',2500);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (11,3,'gaseosa',250);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (12,3,'cerveza nacional',350);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (13,3,'cerveza importada',500);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (14,3,'paquete pringles',300);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (15,3,'paquete oreo',300);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (16,3,'paquete mani',300);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (17,3,'botella espumante chandon',900);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (18,3,'botella espumante navarro correa',1000);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (19,3,'agua mineral sin gas',250);
insert into item_consumo(id_item, id_categoria, nombre, costo) values (20,3,'agua mineral con gas',250);

--Datos Tipo de Tarjeta de Credito
insert into tipocredito (id_tipo_credito, tipo_credito)  values (1, 'visa');
insert into tipocredito (id_tipo_credito, tipo_credito)  values (2, 'master card');
insert into tipocredito (id_tipo_credito, tipo_credito)  values (3, 'amex');
insert into tipocredito (id_tipo_credito, tipo_credito)  values (4, 'tarjeta naranja');

insert into tipodebito (id_tipo_debito, tipo_debito)  values (5, 'maestro');
insert into tipodebito (id_tipo_debito, tipo_debito)  values (6, 'visa debito');
insert into tipodebito (id_tipo_debito, tipo_debito)  values (7, 'cabal debito');

--Datos Tipo de Cheque
insert into tipocheque (id_tipo_cheque, tipo_cheque) values (1,'cheque no a la orden');
insert into tipocheque (id_tipo_cheque, tipo_cheque) values (2, 'cheque posdatado');

--Datos Tipo de Factura
insert into tipodefactura (id_tipo_factura, tipo) values (1, 'A');
insert into tipodefactura (id_tipo_factura, tipo) values (2, 'B');

--Datos Tipo de Moneda
insert into tipomoneda (id_moneda, moneda, simbolo) values (1, 'peso argentino', '$');
insert into tipomoneda (id_moneda, moneda, simbolo) values (2, 'dolar','u$s');
insert into tipomoneda (id_moneda, moneda, simbolo) values (3, 'euro', '€');
insert into tipomoneda (id_moneda, moneda, simbolo) values (4, 'peso uruguayo', '$U');
insert into tipomoneda (id_moneda, moneda, simbolo) values (5, 'yen', '¥');
insert into tipomoneda (id_moneda, moneda, simbolo) values (6, 'franco suizo', 'CFH');
insert into tipomoneda (id_moneda, moneda, simbolo) values (7, 'real', 'R$');
insert into tipomoneda (id_moneda, moneda, simbolo) values (8, 'peso chileno', '$CLP');
insert into tipomoneda (id_moneda, moneda, simbolo) values (9, 'guaranì', '₲');
insert into tipomoneda (id_moneda, moneda, simbolo) values (10, 'sol peruano', 'S/');

--Datos Consumo
insert into consumos_ocupacion (id_ocupacion, id_consumo) values (idOcup1,1);

insert into unidades ( fecha_consumo, unidades) values ('2021-12-12', 2);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (1, 1, 1);

insert into unidades ( fecha_consumo, unidades) values ('2021-12-12', 4);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (2, 1, 2);

insert into unidades ( fecha_consumo, unidades) values ('2021-12-12', 1);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (3, 1, 11);

insert into personajuridica (idpersona, razonsocial, domiciliofiscal) VALUES (id11, 'Tesla S.A.', '1st Street, 5431');

end$$;