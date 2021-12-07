create table posIVA (
	ident int,
	tipo character varying(50),
	constraint pk_ident primary key (ident)
);

create table pais(
	nombre character varying(50),
	codigoPais int,
	nacionalidad character varying(50),
	constraint pk_codigoPais primary key (codigoPais)
);

create table provincia(
	nombre character varying(50),
	codigoProvincia int,
	pais int,
	constraint pk_codigoProv primary key (codigoProvincia),
	constraint fk_pais foreign key (pais) REFERENCES pais (codigoPais)
);

create table localidad(
	nombre character varying(50),
	codPostal character varying(10),
	codigoLocalidad int,
	prov int,
	constraint pk_codigoLocalidad primary key (codigoLocalidad),
	constraint fk_prov foreign key (prov) REFERENCES provincia (codigoProvincia)
);

create table persona(
	idPersona int GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
	telefono character varying(25),
	email character varying(50),
	CUIT character varying(50) NOT NULL,
	calle character varying(25),
	altura character varying(20),
	PosIVA int,
	Localidad int,
	constraint fk_PosIVA foreign key (PosIVA) REFERENCES posIVA (ident),
	constraint fk_Localidad foreign key (Localidad) REFERENCES localidad (codigoLocalidad)
);

create table IDType(
	tipoDeID character varying(10),
	constraint pk_Tipo primary key (tipoDeID)
);

create table pasajero(
    idPersona int,
	nombre character varying(50),
	apellido character varying(50),
	Ndoc character varying(25),
	TipoDoc character varying(10),
	Ocupacion character varying(50),
	FechaNac date,
	Nacionalidad int,
	constraint fk_idpersona foreign key (idPersona) REFERENCES persona (idPersona),
	constraint pk_idPersona primary key (idPersona),
	constraint fk_nacionalidad foreign key (Nacionalidad) REFERENCES pais (codigoPais),
	constraint fk_TipoDoc foreign key (TipoDoc) REFERENCES IDtype (tipoDeID)
);


create table tipohabitacion (
    id int,
    tipo character varying(50),
    costo double precision,
    constraint pk_id primary key (id)
);

create table habitacion (
    numero int,
    piso int,
    tipo int,
    capacidad int,
    descuento int,
    constraint pk_habitacion primary key (numero, piso),
    constraint fk_tipo foreign key (tipo) REFERENCES tipohabitacion (id)
);

create table ocupacion (
    id_ocupacion int,
    numero int,
    piso int,
    responsable int,
    checkin date,
    checkout date,
    constraint pk_id_ocupacion primary key (id_ocupacion),
    constraint fk_habitacion foreign key (numero, piso) REFERENCES habitacion (numero, piso),
    constraint fk_responsable foreign key (responsable) REFERENCES pasajero (idPersona)
);

create table reserva (
    id_reserva int,
    numero int,
    piso int,
    nombre character varying(50),
    apellido character varying(50),
    telefono character varying(50),
    fechadesde date,
    fechahasta date,
    constraint pk_id_reserva primary key (id_reserva),
    constraint fk_habitacion foreign key (numero, piso) REFERENCES habitacion (numero, piso)
);

create table fueradeservicio (
    id_fueradeservicio int,
    desde date,
    hasta date,
    numero int,
    piso int,
    constraint pk_id_fueradeservicio primary key (id_fueradeservicio),
    constraint fk_habitacion foreign key (numero, piso) REFERENCES habitacion (numero, piso)
);
