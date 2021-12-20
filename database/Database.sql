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

create table personajuridica(
    idPersona int,
    RazonSocial character varying(50),
    DomicilioFiscal character varying(100),
    constraint fk_idpersona foreign key (idPersona) REFERENCES persona (idPersona)
);

create table responsabledepago(
    idPersona int PRIMARY KEY,
    RazonSocial character varying(50),
    CUITDNI character varying(50),
    NumDireccion character varying(50),
    calle character varying(50),
    telefono character varying(50),
    constraint fk_idpersona foreign key (idPersona) REFERENCES persona (idPersona)
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
    id_ocupacion int GENERATED ALWAYS AS IDENTITY NOT NULL,
    numero int,
    piso int,
    responsable int,
    checkin date,
    checkout date,
    constraint pk_id_ocupacion primary key (id_ocupacion),
    constraint fk_habitacion foreign key (numero, piso) REFERENCES habitacion (numero, piso),
    constraint fk_responsable foreign key (responsable) REFERENCES pasajero (idPersona)
);

create table acompanantes (
    pasajero int,
    ocupacion int,
    constraint pk_acompanantes primary key (pasajero, ocupacion),
    constraint fk_ocupacion foreign key (ocupacion) REFERENCES ocupacion (id_ocupacion),
    constraint fk_pasajero foreign key (pasajero) REFERENCES pasajero (idPersona)
);

create table unidades (
    id_unidades int PRIMARY KEY,
    fecha_consumo Date,
    unidades int NOT NULL
);

create table seccion_consumo (
    id_categoria int PRIMARY KEY,
    tipo character varying(50)
);

create table item_consumo (
    id_item int PRIMARY KEY ,
    id_categoria int NOT NULL,
    nombre character varying(50),
    costo double precision,
    constraint fk_categoria foreign key (id_categoria) REFERENCES seccion_consumo (id_categoria)
);

create table periodoestadia(
    id_estadia int PRIMARY KEY,
    id_ocupacion int,
    fechadesde Date,
    fechahasta Date,
    monto double precision,
    constraint fk_ocupacion foreign key (id_ocupacion) REFERENCES ocupacion (id_ocupacion)
);

create table detalleunidades (
    id_detalle_unidades int PRIMARY KEY,
	id_unidades int NOT NULL,
    id_detalle int NOT NULL,
    id_item int NOT NULL,
    constraint fk_unidades foreign key (id_unidades) REFERENCES unidades (id_unidades),
    constraint fk_item foreign key (id_item) REFERENCES item_consumo (id_item)
);

create table detallefactura (
    id_detalle int PRIMARY KEY,
    costoTotal double precision,
    id_factura int NOT NULL,
    id_detalle_unidades int,
    constraint fk_detalle_unidades foreign key (id_detalle_unidades) REFERENCES detalleunidades (id_detalle_unidades)
);

create table tipodefactura (
	id_tipo_factura int PRIMARY KEY,
	tipo character varying(50)
);

create table notadecredito (
	id_nota_de_credito int PRIMARY KEY,
	responsable_pago character varying(100),
	fechafactura date,
	imp_neto double precision,
	iva double precision,
	couit_dni character varying(50)
);

create table pago (
	id_pago int PRIMARY KEY,
	monto double precision
);

create table factura(
    id_factura int PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    id_estadia int NOT NULL,
    id_detalle int NOT NULL,
    id_responsable int NOT NULL,
    id_tipo_factura int NOT NULL,
    id_nota_de_credito int NOT NULL,
    id_pago int,
    fecha Date,
    MontoTotal double precision,
    constraint fk_estadia foreign key (id_estadia) REFERENCES periodoestadia (id_estadia),
    constraint fk_detalle foreign key (id_detalle) REFERENCES detallefactura (id_detalle),
    constraint fk_responsable foreign key (id_responsable) REFERENCES responsabledepago (idPersona),
    constraint fk_tipo_factura foreign key (id_tipo_factura) REFERENCES tipodefactura (id_tipo_factura),
    constraint fk_id_nota foreign key (id_nota_de_credito) REFERENCES notadecredito (id_nota_de_credito),
    constraint fk_id_pago foreign key (id_pago) REFERENCES pago (id_pago)
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

create table tipomoneda (
    id_moneda int PRIMARY KEY,
    moneda character varying(25),
    simbolo character varying(5)
);

create table efectivo (
    id_pago int PRIMARY KEY,
    cotizacion double precision,
    id_moneda int NOT NULL,
    constraint fk_pago foreign key (id_pago) REFERENCES pago (id_pago),
    constraint fk_moneda foreign key (id_moneda) REFERENCES tipomoneda (id_moneda)
);

create table tipoCheque (
    id_tipo_cheque int PRIMARY KEY,
    tipo_cheque character varying(25)
);

create table cheque (
    id_pago int PRIMARY KEY,
    num_cheque character varying(50),
    banco character varying(50),
    plaza character varying(50),
    fecha Date,
    id_tipo_cheque int NOT NULL,
    constraint fk_pago foreign key (id_pago) REFERENCES pago (id_pago),
    constraint fk_tipo_cheque foreign key (id_tipo_cheque) REFERENCES tipoCheque (id_tipo_cheque)
);

create table tarjeta (
    id_pago int PRIMARY KEY,
    numero character varying (25),
    vencimiento Date,
    nombre character varying (50),
    dni character varying (25),
    constraint fk_pago foreign key (id_pago) REFERENCES pago (id_pago)
);

create table tipodebito (
    id_tipo_debito int PRIMARY KEY,
    tipo_debito character varying(25)
);

create table tipocredito (
    id_tipo_credito int PRIMARY KEY,
    tipo_credito character varying(25)
);

create table tarjeta_de_credito (
    id_pago int PRIMARY KEY,
    cant_cuotas int NOT NULL,
    recargo int NOT NULL,
    id_tipo_credito int NOT NULL,
    constraint fk_pago foreign key (id_pago) REFERENCES pago (id_pago),
    constraint fk_tipo_credito foreign key (id_tipo_credito) REFERENCES tipocredito (id_tipo_credito)
);

create table tarjeta_de_debito (
    id_pago int PRIMARY KEY,
    id_tipo_debito int NOT NULL,
    constraint fk_pago foreign key (id_pago) REFERENCES pago (id_pago),
    constraint fk_tipo_debito foreign key (id_tipo_debito) REFERENCES tipodebito (id_tipo_debito)
);

create table consumos_ocupacion (
    costoTotal double precision,
    id_ocupacion int NOT NULL,
    id_consumo int PRIMARY KEY,
    constraint fk_ocupacion foreign key (id_ocupacion) REFERENCES ocupacion (id_ocupacion)
);

create table consumo_unidades (
    id_unidades int NOT NULL,
    id_consumo int NOT NULL,
    id_item int NOT NULL,
    constraint pk_consumo_unidades PRIMARY KEY (id_unidades , id_consumo, id_item),
    constraint fk_unidades foreign key (id_unidades) REFERENCES unidades (id_unidades),
    constraint fk_consumo foreign key (id_consumo) REFERENCES consumos_ocupacion (id_consumo),
    constraint fk_item foreign key (id_item) REFERENCES item_consumo (id_item)
);


