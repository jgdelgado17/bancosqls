-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- bancos.dbo.Cliente definition

-- Drop table

-- DROP TABLE bancos.dbo.Cliente;

CREATE TABLE bancos.dbo.Cliente (
	id bigint NOT NULL,
	direccion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	edad int NOT NULL,
	genero varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	identificacion varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	nombre varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	telefono varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	contrasenia varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	estado bit NOT NULL,
	CONSTRAINT PK__Cliente__3213E83FBC11A357 PRIMARY KEY (id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_4l7ftruxsjh5qf8hwftwt52rj ON dbo.Cliente (  identificacion ASC  )  
	 WHERE  ([identificacion] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;
 CREATE  UNIQUE NONCLUSTERED INDEX UK_ryt8ieka7jtt5ply7xwuigudc ON dbo.Cliente (  contrasenia ASC  )  
	 WHERE  ([contrasenia] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;
ALTER TABLE bancos.dbo.Cliente WITH NOCHECK ADD CONSTRAINT CK__Cliente__genero__37A5467C CHECK ([genero]='m' OR [genero]='f');


-- bancos.dbo.hibernate_sequences definition

-- Drop table

-- DROP TABLE bancos.dbo.hibernate_sequences;

CREATE TABLE bancos.dbo.hibernate_sequences (
	sequence_name varchar(255) COLLATE Modern_Spanish_CI_AS NOT NULL,
	next_val bigint NULL,
	CONSTRAINT PK__hibernat__666199D5B0F366AB PRIMARY KEY (sequence_name)
);


-- bancos.dbo.cuenta definition

-- Drop table

-- DROP TABLE bancos.dbo.cuenta;

CREATE TABLE bancos.dbo.cuenta (
	id bigint IDENTITY(1,1) NOT NULL,
	estado bit NOT NULL,
	numero_cuenta varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	saldo real NOT NULL,
	tipo_cuenta varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	cliente_id bigint NULL,
	CONSTRAINT PK__cuenta__3213E83F6FDFAFDF PRIMARY KEY (id),
	CONSTRAINT cuenta_cliente_fk FOREIGN KEY (cliente_id) REFERENCES bancos.dbo.Cliente(id)
);
 CREATE  UNIQUE NONCLUSTERED INDEX UK_pj7ncg765kt4klndu25bwbwe4 ON dbo.cuenta (  numero_cuenta ASC  )  
	 WHERE  ([numero_cuenta] IS NOT NULL)
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;
ALTER TABLE bancos.dbo.cuenta WITH NOCHECK ADD CONSTRAINT CK__cuenta__tipo_cue__3A81B327 CHECK ([tipo_cuenta]='Ahorro' OR [tipo_cuenta]='Corriente');


-- bancos.dbo.movimiento definition

-- Drop table

-- DROP TABLE bancos.dbo.movimiento;

CREATE TABLE bancos.dbo.movimiento (
	id bigint IDENTITY(1,1) NOT NULL,
	fecha datetime2(6) NULL,
	saldo_inicial real NOT NULL,
	tipo_movimiento varchar(255) COLLATE Modern_Spanish_CI_AS NULL,
	valor real NOT NULL,
	cuenta_id bigint NULL,
	CONSTRAINT PK__movimien__3213E83F8C7F83A0 PRIMARY KEY (id),
	CONSTRAINT movimiento_cuenta_fk FOREIGN KEY (cuenta_id) REFERENCES bancos.dbo.cuenta(id)
);
ALTER TABLE bancos.dbo.movimiento WITH NOCHECK ADD CONSTRAINT CK__movimient__tipo___3F466844 CHECK ([tipo_movimiento]='Retiro' OR [tipo_movimiento]='Deposito');


