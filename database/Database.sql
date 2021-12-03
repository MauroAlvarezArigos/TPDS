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
	codigoLocalidad character varying(10),
	prov int,
	constraint pk_codigoLocalidad primary key (codigoLocalidad),
	constraint fk_prov foreign key (prov) REFERENCES provincia (codigoProvincia)
);

create table persona(
	idPersona int,
	telefono character varying(25),
	email character varying(50),
	CUIT character varying(50) NOT NULL,
	calle character varying(25),
	altura character varying(20),
	PosIVA int,
	Localidad character varying(10),
	constraint fk_PosIVA foreign key (PosIVA) REFERENCES posIVA (ident),
	constraint fk_Localidad foreign key (Localidad) REFERENCES localidad (codigoLocalidad),
	constraint pk_ID primary key(idPersona)
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
	constraint pk_Ndoc primary key (Ndoc),
	constraint fk_nacionalidad foreign key (Nacionalidad) REFERENCES pais (codigoPais),
	constraint fk_TipoDoc foreign key (TipoDoc) REFERENCES IDtype (tipoDeID)
);
