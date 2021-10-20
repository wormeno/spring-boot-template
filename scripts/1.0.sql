ALTER TABLE `sys_usuario` CHANGE `id_sys_usuario` `id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `snic` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `lote`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_departamento` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `con_relacion_seccional_mucipalidad`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_seccional` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `stamp_fecha_alta`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_agrupacion` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `stamp_fecha_alta`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_mes` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `nombre`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_provincia` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `activo`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `aux_estado` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `nombre`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;

ALTER TABLE `sys_usuario` ADD `fecha_creacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `borrar_validar_codigo`,
    ADD `fecha_ultima_modificacion` TIMESTAMP NULL AFTER `fecha_creacion`;



