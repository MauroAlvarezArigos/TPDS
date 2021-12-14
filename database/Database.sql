create table posIVA (
	ident int,
	tipo character varying(50),
	constraint pk_ident primary key (ident)
);

create table unidades (
	id_unidades INT,
	fechaconsumo DATE,
	unidades INT,
	constraint pk_idunidades primary key (id_unidades)
);

create table pais (
	id_pais INT,
	nombre VARCHAR(50),
	nacionalidad VARCHAR(50),
	constraint pk_idpais primary key (id_pais)
);

create table provincia(
	nombre character varying(50),
	codigoProvincia int,
	pais int,
	constraint pk_codigoProv primary key (codigoProvincia),
	constraint fk_pais foreign key (pais) REFERENCES pais (id_pais)
);

create table localidad(
	nombre character varying(50),
	codPostal character varying(10),
	codigoLocalidad int,
	prov int,
	constraint pk_codigoLocalidad primary key (codigoLocalidad),
	constraint fk_prov foreign key (prov) REFERENCES provincia (codigoProvincia)
);

create table IDType(
	tipoDeID character varying(10),
	constraint pk_Tipo primary key (tipoDeID)
);

create table seccionconsumo (
	seccion varchar(50),
	constraint pk_seccion primary key (seccion)
);

create table tipohabitacion (
    id int,
    tipo character varying(50),
    costo double precision,
    constraint pk_id primary key (id)
);

create table itemconsumo (
	id_item INT,
	categoria VARCHAR(50),
	nombre VARCHAR(50),
	costo VARCHAR(50),
	constraint pk_iditem primary key (id_item),
	constraint fk_categoria foreign key (categoria) references seccionconsumo(seccion)
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
	constraint fk_nacionalidad foreign key (Nacionalidad) REFERENCES pais (id_pais),
	constraint fk_TipoDoc foreign key (TipoDoc) REFERENCES IDtype (tipoDeID)
);

create table persona_juridica(
	idPersona int primary key,
	domicilioFiscal character varying(50),
	razonSocial character varying(50),
	constraint fk_idpersona foreign key (idPersona) REFERENCES persona (idPersona)
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

create table consumoservicios (
	id_consumo INT,
	ocupacion int,
	costototal VARCHAR(50),
	constraint pk_idconsumo primary key (id_consumo),
	constraint fk_ocupacion foreign key (ocupacion) references ocupacion (id_ocupacion)
);

create table consumounidades (
	id_unidades INT,
	id_consumo INT,
	id_item INT,
	constraint fk_idunidades foreign key (id_unidades) references unidades(id_unidades),
	constraint fk_idconsumo foreign key (id_consumo) references consumoservicios(id_consumo),
	constraint fk_iditem foreign key (id_item) references itemconsumo(id_item),
	constraint pf_consumo_unidades primary key (id_unidades, id_consumo, id_item)
);

create table periodoestadia (
	id_estadia INT,
	ocupacion int,
	fechainicio DATE,
	fechafinal DATE,
	monto VARCHAR(50),
	constraint pk_idestadia primary key (id_estadia),
	constraint fk_ocupacion foreign key (ocupacion) references ocupacion (id_ocupacion)
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

create table notadecredito (
	id_nota INT,
	ResponsablePago INT,
	FechaFactura DATE,
	ImpNeto DECIMAL(6,2),
	IVA DECIMAL(6,2),
	DNICUIT VARCHAR(10),
	constraint pk_notadecredito primary key (id_nota)
);

create table RespDePago (
	id_respDePago INT,
	id_Persona INT,
	DNICUIT VARCHAR(50),
	NumDireccion VARCHAR(50),
	Telefono VARCHAR(50),
	constraint pk_respdepago primary key (id_respDePago)
);

create table factura (
	id_factura INT,
	id_estadia INT,
	nota_credito int,
	id_respdepago INT,
	fecha DATE,
	monto_total VARCHAR(50),
	constraint pk_factura primary key (id_factura),
	constraint fk_estadia foreign key (id_estadia) references periodoestadia(id_estadia),
	constraint fk_notacredito foreign key (nota_credito) references notadecredito(id_nota),
	constraint fk_resp_pago foreign key (id_respdepago) references respdepago(id_respdepago)
);

create table pago (
	id_pago INT,
	id_factura INT,
	monto VARCHAR(50),
	constraint pk_pago primary key (id_pago),
	constraint fk_factura foreign key (id_factura) references factura(id_factura)
);

create table detallesfactura (
	id_factura INT,
	id_detalle INT,
	costototal VARCHAR(50),
	constraint pk_detallesfactura primary key (id_detalle),
	constraint fk_factura foreign key (id_factura) references factura(id_factura)
);

create table detalleunidades (
	id_unidades INT,
	id_detalle int,
	id_item int,
	constraint pk_detalleunidades primary key (id_item,id_unidades,id_detalle),
	constraint fk_item foreign key (id_item) references itemconsumo(id_item),
	constraint fk_unidades foreign key (id_unidades) references unidades(id_unidades),
	constraint fk_detalle foreign key (id_detalle) references detallesfactura(id_detalle)
);
