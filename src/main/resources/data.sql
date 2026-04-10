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
values ('Chevrolet', 'Celta', 'ABC-1234', 2009, 'vermelho', 150.00)