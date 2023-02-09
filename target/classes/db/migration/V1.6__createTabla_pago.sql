create table pago (
         id_pago bigserial PRIMARY KEY,
         numero_referencia_comprobante varchar(64) NOT NULL,
         monto_deuda_total varchar(64) NOT NULL,
         monto_abonado varchar(64) NOT NULL,
         id_usuario bigint NOT NULL,
         id_servicio bigint NOT NULL,
         fecha_registro timestamp without time zone not null,
         status boolean NOT NULL
);

alter table pago add CONSTRAINT fk_pago_usuario
    FOREIGN KEY(id_usuario)
        REFERENCES usuario(id_usuario);

alter table pago add CONSTRAINT fk_pago_servicio
    FOREIGN KEY(id_servicio)
        REFERENCES servicio(id_servicio);