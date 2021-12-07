create table consumounidades (
	id_unidades INT,
	id_consumo INT,
	id_item INT,
	constraint fk_idunidades foreign key (id_unidades) references unidades(id_unidades),
	constraint fk_idconsumo foreign key (id_consumo) references consumoservicios(id_consumo),
	constraint fk_iditem foreign key (id_item) references itemconsumo(id_item),
	constraint pf_consumo_unidades primary key (id_unidades, id_consumo, id_item)
);
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
