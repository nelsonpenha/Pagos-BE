create table usuario_rol
(
    id_usuario int not null,
    id_rol int not null
);

create unique index usuario_rol_usuario_id_rol_id_uindex
	on usuario_rol (id_usuario, id_rol);

alter table usuario_rol
    add constraint usuario_rol_rol_id_fk
        foreign key (id_rol) references rol;

alter table usuario_rol
    add constraint usuario_rol_usuario_id_fk
        foreign key (id_usuario) references usuario;
