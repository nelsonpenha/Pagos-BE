create table deuda (
         id_deuda bigserial PRIMARY KEY,
         monto_deuda varchar(64) NOT NULL,
         id_usuario bigint NOT NULL,
         id_servicio bigint NOT NULL,
         status boolean NOT NULL
);

create unique index deuda_id_usuario_id_usuario_uindex
	on deuda (id_usuario, id_servicio);

alter table deuda add CONSTRAINT fk_deuda_usuario
    FOREIGN KEY(id_usuario)
        REFERENCES usuario(id_usuario);