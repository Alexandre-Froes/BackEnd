drop database if exists db_transacao;
create database db_transacao;
use db_transacao;

create table clientes(
    cliente_id int primary key auto_increment,
    nome varchar(255),
    conta double(10,2)
);

create table produtos (
    produto_id int primary key auto_increment,
    nome varchar(255),
    preco double(10,2),
    estoque int
);

create table pedidos (
    pedido_id int primary key auto_increment,
    preco_venda double(10,2),
    quantidade int,
    produto_id int,
    cliente_id int,
    status varchar(255),
    foreign key (cliente_id) references clientes (cliente_id),
    foreign key (produto_id) references produtos (produto_id)
);

-- Inserções
INSERT INTO clientes (nome, conta)
VALUES ('Ana Paula Silva', 1500.75), 
       ('Roberto Carlos Souza', 3200.00);

INSERT INTO produtos (nome, preco, estoque)
VALUES ('Teclado Mecânico RGB', 350.00, 150), 
       ('Mouse Gamer Wireless', 220.50, 80);

INSERT INTO pedidos (preco_venda, quantidade, produto_id, cliente_id, status)
VALUES (350.00, 2, 1, 1, "concluido"),
       (220.50, 1, 2, 2, "concluido");

select * from pedidos;
