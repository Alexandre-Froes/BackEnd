create database menefreda;
use menefreda;

create table paciente (
cpf_paci char(11) primary key,
nome_paci varchar(50),
genero_paci char(1),
email_paci varchar(100),
dt_nasc_paci date,
end_paci varchar(50),
tel_paci varchar(11)
);

create table plano (
cod_plano int primary key auto_increment,
valor_plano float,
tipo_plano varchar(20)
);

create table medico (
cod_med int primary key,
nome_med varchar(50),
area_atua varchar(30),
tel_med varchar(11)
);