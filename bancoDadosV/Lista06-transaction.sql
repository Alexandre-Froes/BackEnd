drop database PoupeBem;
create database PoupeBem;
use PoupeBem;

create table cliente(
	id_cliente int auto_increment,
	nome varchar(50),
	telefone char(15),
	endereco varchar(50),
	email varchar(100),
	primary key(id_cliente));

create table conta_corrente(
	id_conta int auto_increment,
	saldo numeric(9,2),
	id_cliente int,
	primary key(id_conta),
	foreign key(id_cliente)references cliente(id_cliente));

create table conta_poupanca(
	id_poupanca int auto_increment,
	saldo numeric(9,2),
	id_cliente int,
	primary key(id_poupanca),
	foreign key(id_cliente)references cliente(id_cliente));
    
Select * from cliente;
Set autocommit=0;
Begin work;
	insert into cliente
	values (1,'Amoedo','11999786543','Av. Paulista, n. 100', 'amo.edo@gmail,com'),
	(2, 'Luiz','12999786549','Av. do Triplex, n. 200', 'lu.la@gmail,com');
Select * from cliente;
Commit;

begin work;
insert into conta_corrente
	values (1,'1000',1);
	select * from conta_corrente;
	insert into conta_poupanca
	values (1,'10000',1);
	select * from conta_poupanca;
Commit;

create table deposito_conta(
	id_deposito int auto_increment,
	id_conta int,
	valor_deposito numeric(9,2),
	data_dep datetime,
	primary key(id_deposito),
	foreign key(id_conta) references conta_corrente(id_conta));
    
    
-- TRANSACTION
begin work;
	insert into deposito_conta(id_conta,valor_deposito,data_dep)
	values(1, 2000, current_date);

	update conta_corrente set saldo=saldo+(
										select valor_deposito from deposito_conta
										where id_deposito=(select last_insert_id())
                                        )
										where id_conta=1;

	select * from conta_corrente;
commit;
-- O novo valor vai ser 3k já que foi adicionado 2k para sua conta.


create table saque_conta(
	id_saque int auto_increment,
	id_conta int,
	valor_saque numeric(9,2),
	data_saque datetime,
	primary key(id_saque),
	foreign key(id_conta) references conta_corrente(id_conta));
    
    
    select * from conta_corrente;
begin work;
	-- savepoint antes_insert;
	insert into saque_conta(id_conta, valor_saque, data_saque)
	values(1, 1000, now());
    -- rollback to savepoint antes_insert;
    
    -- savepoint antes_update;
    update conta_corrente set saldo = saldo - (
								select valor_saque from saque_conta 
								where id_saque=(select last_insert_id())
								)
								where id_conta=1;
                                
	-- rollback to savepoint antes_update;
	select * from conta_corrente;
commit;    


select * from conta_corrente;
select * from conta_poupanca;
begin work;
	update conta_corrente
    set saldo = saldo - 1000
    where id_conta = 1;
    
	update conta_poupanca
    set saldo = saldo + 1000
    where id_poupanca = 1;

	select * from conta_corrente;
	select * from conta_poupanca;
commit;

    
