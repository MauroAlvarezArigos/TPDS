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

begin
--Datos Pos Iva
insert into posiva (ident, tipo) values (1, 'consumidor final');
insert into posiva (ident, tipo) values (2, 'monotributista');
insert into posiva (ident, tipo) values (3, 'responsable inscripto');

--unidades
insert into unidades (id_unidades, fechaconsumo, unidades) values (019, '21/03/2021', 15);
insert into unidades (id_unidades, fechaconsumo, unidades) values (029, '21/10/2021', 22);
insert into unidades (id_unidades, fechaconsumo, unidades) values (039, '12/11/2020', 30);
insert into unidades (id_unidades, fechaconsumo, unidades) values (049, '02/08/2021', 4);
insert into unidades (id_unidades, fechaconsumo, unidades) values (059, '26/01/2021', 5);
insert into unidades (id_unidades, fechaconsumo, unidades) values (069, '01/03/2021', 6);
insert into unidades (id_unidades, fechaconsumo, unidades) values (079, '04/11/2021', 7);
insert into unidades (id_unidades, fechaconsumo, unidades) values (089, '14/10/2021', 8);
insert into unidades (id_unidades, fechaconsumo, unidades) values (099, '20/07/2021', 9);
insert into unidades (id_unidades, fechaconsumo, unidades) values (0119, '16/03/2021', 10);

--Datos Pais
insert into pais (id_pais, nombre, nacionalidad) values (054, 'Argentina', 'Argentina');
insert into pais (id_pais, nombre, nacionalidad) values (055, 'Colombia', 'Colombia');
insert into pais (id_pais, nombre, nacionalidad) values (056, 'Vietnam', 'Vietnam');
insert into pais (id_pais, nombre, nacionalidad) values (057, 'Ecuador', 'Ecuador');
insert into pais (id_pais, nombre, nacionalidad) values (058, 'Brasil', 'Brasil');
insert into pais (id_pais, nombre, nacionalidad) values (059, 'Chile', 'Chile');
insert into pais (id_pais, nombre, nacionalidad) values (060, 'Uruguay', 'Uruguay');
insert into pais (id_pais, nombre, nacionalidad) values (061, 'Cuba', 'Cuba');
insert into pais (id_pais, nombre, nacionalidad) values (062, 'Estados Unidos', 'Estados Unidos');
insert into pais (id_pais, nombre, nacionalidad) values (063, 'Mexico', 'Mexico');

--Datos Provincia
insert into provincia (nombre, codigoProvincia, Pais) values ('Santa fe', 1, 054);
insert into provincia (nombre, codigoProvincia, Pais) values ('Entre Rios', 2, 054);
insert into provincia (nombre, codigoProvincia, Pais) values ('Texas', 3, 062);
insert into provincia (nombre, codigoProvincia, Pais) values ('Florida', 4, 062);
insert into provincia (nombre, codigoProvincia, Pais) values ('Sao Paulo', 5, 058);
insert into provincia (nombre, codigoProvincia, Pais) values ('Bahia', 6, 058);
insert into provincia (nombre, codigoProvincia, Pais) values ('Ciudad de Mexico', 7, 063);
insert into provincia (nombre, codigoProvincia, Pais) values ('Antofagasta', 8, 059);
insert into provincia (nombre, codigoProvincia, Pais) values ('Valparaiso', 9, 059);
insert into provincia (nombre, codigoProvincia, Pais) values ('Maldonado', 10, 060);

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
insert into tipohabitacion (id, tipo, costo) values (1, 'Simple', 99.99);
insert into tipohabitacion (id, tipo, costo) values (2, 'Doble', 199.99);

--Item consumo
insert into itemconsumo (id_item, categoria, nombre, costo) values (0199, 'Higene', 'PapelHigenico', '$52.95');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0299, 'Bebida sin alcohol', 'Sprite', '$7.57');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0399, 'Belleza', 'Sombra para Ojos', '$92.40');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0499, 'Bebida sin alcohol', 'Cocacola', '$8.36');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0599, 'Comida', 'Hamburguesa con papas', '$77.88');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0699, 'Comida', 'Hamburguesa con papas', '$29.36');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0799, 'Higene', 'Desodorante', '$91.67');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0899, 'Farmacia', 'Ibuprofeno', '$61.10');
insert into itemconsumo (id_item, categoria, nombre, costo) values (0999, 'Farmacia', 'Paracetamol', '$52.54');
insert into itemconsumo (id_item, categoria, nombre, costo) values (01099, 'Bebida con alcohol', 'Vino Blanco', '$35.41');

--Datos Habitacion
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 1, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 1, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 1, 2, 1, 1, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 2, 2, 2, 2, 0);
insert into habitacion (numero, piso, tipo, capacidad, descuento) values ( 3, 2, 1, 2, 0);

--Datos Ocupacion
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (1, 1, 2, id1, '2021-09-21', '2021-09-30');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (2, 1, 2, id2, '2021-09-01', '2021-09-12');
insert into ocupacion (id_ocupacion, numero, piso, responsable, checkin, checkout) values (3, 1, 2, id3, '2021-09-13', '2021-09-15');

--Periodo estadia
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00001, 1, '25/11/2021', '21/05/2023', '$600.86');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00002, 2, '12/01/2021', '22/02/2023', '$6758.76');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00003, 3, '23/03/2021', '23/08/2023', '$8523.47');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00004, 2, '07/09/2021', '06/06/2023', '$2145.57');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00005, 3, '24/07/2021', '16/05/2023', '$7609.79');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00006, 1, '21/02/2021', '03/06/2023', '$5541.41');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00007, 3, '14/09/2021', '19/03/2022', '$9025.69');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00008, 2, '20/10/2021', '31/01/2023', '$6675.94');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00009, 1, '04/01/2021', '06/01/2022', '$4943.51');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (000010,3, '16/04/2021', '18/08/2023', '$1221.74');

--Consumo servicio
insert into consumoservicios (id_consumo, ocupacion, costototal) values (01, 1, '$3724.02');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (02, 2, '$1216.90');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (03, 1, '$4066.48');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (04, 3, '$443.56');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (05, 3, '$4937.15');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (06, 3, '$4785.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (07, 1, '$5041.43');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (08, 2, '$8699.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (09, 2, '$9159.50');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (011, 2, '$9969.75');

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
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3507913321', 'dglasbey0@whitehouse.gov', '20496352893', 'Veith', '78179', 1, 4) returning idpersona into id1;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9841503697', 'adunne1@hexun.com', '9689632486', 'Bartillon', '3', 1, 2)returning idpersona into id2;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4491706689', 'eseddon2@163.com', '3674102395', 'Lake View', '7881', 3, 9)returning idpersona into id3;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '4779066867', 'mhallut3@timesonline.co.uk', '123456789', 'Marcy', '3515', 2, 9)returning idpersona into id4;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '8935833177', 'hshoorbrooke4@who.int', '987654321', 'Cherokee', '5443', 2, 10)returning idpersona into id5;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '3323145720', 'vjesper5@imdb.com', '9632574129', 'Sloan', '4', 1, 10)returning idpersona into id6;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '2258463920', 'chelian6@arstechnica.com', '889632145', 'Armistice', '84', 3, 2)returning idpersona into id7;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9037432352', 'gshakshaft7@photobucket.com', '20456932159', 'Susan', '5514', 3, 7)returning idpersona into id8;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '1816528986', 'rfriedman8@imdb.com', '9632541596', 'Buhler', '624', 2, 3)returning idpersona into id9;
insert into persona ( telefono, email, cuit, calle, altura, posIVA, localidad) values ( '9630048949', 'lthuillier9@wisc.edu', '6325964203', 'Sherman', '1129', 3, 8)returning idpersona into id10;

--Datos Pasajero
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id1, 'Bondy', 'Rylatt', '3808721502', 'LC', 'Office Assistant IV', '2021-09-11', 054);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id2, 'Luce', 'Theml', '9936502027', 'LE', 'Chief Design Engineer', '2021-02-13', 056);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id3, 'Ricki', 'Lapworth', '7519091015', 'Otro', 'Food Chemist', '2021-06-21', 054);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id4, 'Franny', 'Gumme', '9093832379', 'LC', 'Junior Executive', '2021-08-10', 055);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id5, 'Renell', 'St. Quentin', '6694351917', 'DNI', 'Nuclear Power Engineer', '2021-01-19', 057);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id6, 'Colene', 'Rogan', '3828106110', 'Otro', 'Assistant Professor', '2021-02-14', 057);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id7, 'Richy', 'Wieprecht', '3533356477', 'LE', 'Geological Engineer', '2021-06-25', 055);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id8, 'Ewan', 'Latek', '0084924403', 'LC', 'Office Assistant I', '2021-04-03', 055);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id9, 'Shandee', 'Record', '3017832678', 'DNI', 'Biostatistician III', '2021-02-23', 058);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id10, 'Artus', 'Munn', '2688732048', 'DNI', 'Clinical Specialist', '2021-09-20', 057);

--Datos Reserva
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (1, 1, 1, 'Bondy', 'Rylatt', '3808721502', '2021-09-11', '2021-09-20');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (2, 1, 1, 'Franny', 'Gumme', '9093832379', '2021-09-21', '2021-09-30');
insert into reserva (id_reserva, numero, piso, nombre, apellido, telefono, fechadesde, fechahasta) values (3, 1, 1, 'Richy', 'Wieprecht', '3533356477', '2021-09-01', '2021-09-10');

--Datos Fuera De Servicio
insert into fueradeservicio (id_fueradeservicio, numero, piso, desde, hasta) values (1, 2, 1,'2021-09-01', '2021-09-30');

end$$;