create table posIVA (
	ident int,
	tipo character varying(50),
	constraint pk_ident primary key (ident)
);

create table persona(
	telefono character varying(25),
	email character varying(50),
	CUIT character varying(50) NOT NULL,
	calle character varying(25),
	altura integer,
	PosIVA int,
	constraint fk_PosIVA foreign key (PosIVA) REFERENCES posIVA (ident),
	constraint pk_CUIT primary key(CUIT)
);


create table IDType(
	tipoDeID character varying(10),
	constraint pk_Tipo primary key (tipoDeID)
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
	codigoLocalidad character varying(10),
	prov int,
	constraint pk_codigoLocalidad primary key (codigoLocalidad),
	constraint fk_prov foreign key (prov) REFERENCES provincia (codigoProvincia) 
);


create table pasajero(
	nombre character varying(50),
	apellido character varying(50),
	Ndoc character varying(25),
	TipoDoc character,
	Ocupacion character varying(50),
	FechaNac date,
	Nacionalidad int,
	constraint pk_Ndoc primary key (Ndoc),
	constraint fk_nacionalidad foreign key (Nacionalidad) REFERENCES pais (codigoPais),
	constraint fk_TipoDoc foreign key (TipoDoc) REFERENCES IDtype (tipoDeID)
);
