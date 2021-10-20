
    create table sys_usuario (
       id bigint not null auto_increment,
        fecha_creacion datetime,
        fecha_ultima_modificacion datetime,
        apellido varchar(255),
        borrado bit,
        clave varchar(255),
        clave_valida_hasta datetime,
        correo varchar(255),
        fecha_ultimo_intento_fallido datetime,
        intento_login_fallido integer,
        nombre varchar(255),
        telefono varchar(255),
        usuario varchar(255),
        valido_correo bit,
        primary key (id)
    ) engine=InnoDB

    create table sys_usuario (
       id bigint not null auto_increment,
        fecha_creacion datetime,
        fecha_ultima_modificacion datetime,
        apellido varchar(255),
        borrado bit,
        clave varchar(255),
        clave_valida_hasta datetime,
        correo varchar(255),
        fecha_ultimo_intento_fallido datetime,
        intento_login_fallido integer,
        nombre varchar(255),
        telefono varchar(255),
        usuario varchar(255),
        valido_correo bit,
        primary key (id)
    ) engine=InnoDB
