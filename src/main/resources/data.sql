create table veiculos(
    id bigint auto_increment primary key,
    marca varchar(255),
    modelo varchar(255),
    placa varchar(255),
    ano int,
    cor varchar(255),
    valor_diaria decimal(10,2)
);

insert into veiculos(marca, modelo, placa, ano, cor, valor_diaria)
values ('Chevrolet', 'Celta', 'ABC-1234', 2009, 'vermelho', 150.00);

create table pessoas(
     id bigint auto_increment primary key,
     nome varchar(255),
     cpf varchar(255),
     telefone varchar(255),
     email varchar(255)
);

insert into pessoas(nome, cpf, telefone, email)
values ('Miguel Nastri dos Santos', '34099998902', '11972854715', 'miguel@mail.com');


create table alugueis(
     id bigint auto_increment primary key,
     pessoa_id bigint not null,
     veiculo_id bigint not null,
     data_inicio date,
     data_fim date,
     valor_total decimal(10,2),
     foreign key (pessoa_id) references pessoas(id),
     foreign key (veiculo_id) references veiculos(id)
);

insert into alugueis(pessoa_id, veiculo_id, data_inicio, data_fim, valor_total)
values (1,1,'2025-10-01', '2025-10-15', 1500.00);