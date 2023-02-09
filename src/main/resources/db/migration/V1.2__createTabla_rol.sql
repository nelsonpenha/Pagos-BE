create table rol
(
    id_rol bigserial not null,
    rol_nombre varchar(256) not null
);

create unique index rol_id_uindex
	on rol (id_rol);

alter table rol
    add constraint rol_pk
        primary key (id_rol);
