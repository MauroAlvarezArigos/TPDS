create table consumoservicios (
	id_consumo INT,
	ocupacion int,
	costototal VARCHAR(50),
	constraint pk_idconsumo primary key (id_consumo),
	constraint fk_ocupacion foreign key (ocupacion) references ocupacion (id_ocupacion)
);
insert into consumoservicios (id_consumo, ocupacion, costototal) values (01, 101010, '$3724.02');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (02, 202020, '$1216.90');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (03, 303030, '$4066.48');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (04, 404040, '$443.56');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (05, 505050, '$4937.15');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (06, 606060, '$4785.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (07, 707070, '$5041.43');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (08, 808080, '$8699.82');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (09, 909090, '$9159.50');
insert into consumoservicios (id_consumo, ocupacion, costototal) values (011, 101101, '$9969.75');
