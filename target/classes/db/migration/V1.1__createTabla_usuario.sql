create table usuario
(
    id_usuario bigserial not null,
    email varchar(256) not null,
    nombre varchar(256) not null,
    numero_documento varchar(256) not null,
    password varchar(256) not null
);

create unique index usuario_id_uindex
	on usuario (id_usuario);

create unique index usuario_numero_documento_uindex
	on usuario (numero_documento);

create unique index usuario_email_uindex
	on usuario (email);

alter table usuario
    add constraint usuario_pk
        primary key (id_usuario);
