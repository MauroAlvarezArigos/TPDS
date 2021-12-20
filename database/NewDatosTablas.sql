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

--Datos Pais
insert into pais (nombre, codigoPais, nacionalidad) values ('Chile', 1, 'Chileno');
insert into pais (nombre, codigoPais, nacionalidad) values ('Ukraine', 2, 'Uckraniano');
insert into pais (nombre, codigoPais, nacionalidad) values ('Rusia', 3, 'Ruso');
insert into pais (nombre, codigoPais, nacionalidad) values ('Brazil', 4, 'Brasilero');
insert into pais (nombre, codigoPais, nacionalidad) values ('France', 5, 'Frances');
insert into pais (nombre, codigoPais, nacionalidad) values ('Turquia', 6, 'Turco');
insert into pais (nombre, codigoPais, nacionalidad) values ('Argentina', 7, 'Argentino');
insert into pais (nombre, codigoPais, nacionalidad) values ('United States', 8, 'Americano');
insert into pais (nombre, codigoPais, nacionalidad) values ('Philippines', 9, 'Filipino');
insert into pais (nombre, codigoPais, nacionalidad) values ('Japon', 10, 'Japones');

--Datos Provincia
insert into provincia (nombre, codigoProvincia, Pais) values ('Santa fe', 1, '7');
insert into provincia (nombre, codigoProvincia, Pais) values ('Entre Rios', 2, '7');
insert into provincia (nombre, codigoProvincia, Pais) values ('Texas', 3, '8');
insert into provincia (nombre, codigoProvincia, Pais) values ('Florida', 4, '8');
insert into provincia (nombre, codigoProvincia, Pais) values ('Sao Paulo', 5, '4');
insert into provincia (nombre, codigoProvincia, Pais) values ('Bahia', 6, '4');
insert into provincia (nombre, codigoProvincia, Pais) values ('Moscu', 7, '3');
insert into provincia (nombre, codigoProvincia, Pais) values ('Antofagasta', 8, '1');
insert into provincia (nombre, codigoProvincia, Pais) values ('Valpara�so', 9, '1');
insert into provincia (nombre, codigoProvincia, Pais) values ('Normand�a', 10, '5');

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
insert into localidad (nombre, codPostal, codigoLocalidad, prov) values ('Valpara�so', '9345', 10, '9');

--Datos IDType
insert into IDType (tipoDeID) values ('DNI');
insert into IDType (tipoDeID) values ('LC');
insert into IDType (tipoDeID) values ('Otro');
insert into IDType (tipoDeID) values ('LE');
insert into IDType (tipoDeID) values ('Pasaporte');

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
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id1, 'Bondy', 'Rylatt', '3808721502', 'LC', 'Office Assistant IV', '2021-09-11', 1);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id2, 'Luce', 'Theml', '9936502027', 'LE', 'Chief Design Engineer', '2021-02-13', 1);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id3, 'Ricki', 'Lapworth', '7519091015', 'Otro', 'Food Chemist', '2021-06-21', 4);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id4, 'Franny', 'Gumme', '9093832379', 'LC', 'Junior Executive', '2021-08-10', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id5, 'Renell', 'St. Quentin', '6694351917', 'DNI', 'Nuclear Power Engineer', '2021-01-19', 7);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id6, 'Colene', 'Rogan', '3828106110', 'Otro', 'Assistant Professor', '2021-02-14', 7);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id7, 'Richy', 'Wieprecht', '3533356477', 'LE', 'Geological Engineer', '2021-06-25', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id8, 'Ewan', 'Latek', '0084924403', 'LC', 'Office Assistant I', '2021-04-03', 5);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id9, 'Shandee', 'Record', '3017832678', 'DNI', 'Biostatistician III', '2021-02-23', 8);
insert into pasajero (idpersona, nombre, apellido, Ndoc, TipoDoc, Ocupacion, FechaNac, nacionalidad) values (id10, 'Artus', 'Munn', '2688732048', 'DNI', 'Clinical Specialist', '2021-09-20', 7);

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
insert into ocupacion ( numero, piso, responsable, checkin, checkout) values ( 1, 2, id3, '2021-12-13', '2021-12-30');

--Datos Fuera De Servicio
insert into fueradeservicio (id_fueradeservicio, numero, piso, desde, hasta) values (1, 2, 1,'2021-09-01', '2021-09-30');

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
insert into tipodefactura (id_tipo_factura, tipo) values (1, 'Factura A responsable inscripto');
insert into tipodefactura (id_tipo_factura, tipo) values (2, 'Factura B consumidor final');
insert into tipodefactura (id_tipo_factura, tipo) values (3, 'Factura B sujeto exento');

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
insert into consumos_ocupacion (id_ocupacion, id_consumo) values (4,1);

insert into unidades (id_unidades, fecha_consumo, unidades) values (1, '2021-12-12', 2);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (1, 1, 1);

insert into unidades (id_unidades, fecha_consumo, unidades) values (2, '2021-12-12', 4);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (2, 1, 2);

insert into unidades (id_unidades, fecha_consumo, unidades) values (3, '2021-12-12', 1);
insert into consumo_unidades (id_unidades, id_consumo, id_item) values (3, 1, 11);


end$$;