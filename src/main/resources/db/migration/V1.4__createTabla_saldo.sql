create table saldo (
         id_saldo bigserial PRIMARY KEY,
         monto_saldo varchar(64) NOT NULL,
         id_usuario bigint NOT NULL,
         status boolean NOT NULL
);

create unique index saldo_id_usuario_uindex
	on saldo (id_usuario);

alter table saldo add CONSTRAINT fk_saldo_usuario
    FOREIGN KEY(id_usuario)
        REFERENCES usuario(id_usuario);