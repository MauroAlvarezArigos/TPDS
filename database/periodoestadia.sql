create table periodoestadia (
	id_estadia INT,
	ocupacion int,
	fechainicio DATE,
	fechafinal DATE,
	monto VARCHAR(50),
	constraint pk_idestadia primary key (id_estadia),
	constraint fk_ocupacion foreign key (ocupacion) references ocupacion (id_ocupacion),
);
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00001, 101010, '11/25/2021', '5/21/2023', '$600.86');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00002, 202020, '12/1/2021', '2/22/2023', '$6758.76');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00003, 303030, '3/20/2021', '8/23/2023', '$8523.47');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00004, 404040, '7/9/2021', '6/6/2023', '$2145.57');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00005, 505050, '7/24/2021', '5/16/2023', '$7609.79');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00006, 606060, '2/27/2021', '5/3/2023', '$5541.41');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00007, 707070, '9/14/2021', '3/19/2022', '$9025.69');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00008, 808080, '10/20/2021', '1/31/2023', '$6675.94');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (00009, 909090, '4/1/2021', '6/1/2022', '$4943.51');
insert into periodoestadia (id_estadia, ocupacion, fechainicio, fechafinal, monto) values (101101, 'Mechanical Systems Engineer', '4/16/2021', '8/18/2023', '$1221.74');
