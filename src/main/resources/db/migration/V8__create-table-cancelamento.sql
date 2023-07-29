create table cancelamentos(

    id bigint not null auto_increment,
    consulta_id bigint not null,
    motivo varchar(500) not null,

    primary key(id),
    constraint fk_cancelamentos_consulta_id foreign key (consulta_id) references consultas(id)
)