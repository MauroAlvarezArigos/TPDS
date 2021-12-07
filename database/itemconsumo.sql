create table itemconsumo (
	id_item INT,
	categoria VARCHAR(50),
	nombre VARCHAR(50),
	costo VARCHAR(50),
	constraint pk_iditem primary key (id_item),
	constraint fk_categoria foreing key (categoria) references seccionconsumo(seccion)
);
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

create table seccionconsumo (
	seccion varchar(50),
	constraint pk_seccion primary key (seccion)
);

insert into seccionconsumo (seccion) values ('Higene');
insert into seccionconsumo (seccion) values ('Bebida sin alcohol');
insert into seccionconsumo (seccion) values ('Belleza');
insert into seccionconsumo (seccion) values ('Comida');
insert into seccionconsumo (seccion) values ('Farmacia');
insert into seccionconsumo (seccion) values ('Bebida con alcohol');
