drop database theGame;
create database theGame;
use theGame;

create table Jogador(
cod_jogador int primary key,
nome varchar(50),
nickname varchar(20),
sexo char(1),
email varchar(100),
dt_nasc date,
pontuacao int,
moedas int);

insert into Jogador
values(1,"Roberto Carlos","robcar","M","robcar@gmail.com","2000-08-07",1000,3000),
(2,"Maria Clara","marcla","F","marcla@gmail.com","1999-05-07",5000,500),
(3,"João Marcos","jomar","M","jomar@gmail.com","1998-01-15",1000,5000),
(4,"Karina Jones","kjones","F","kjones@yahoo.com","1999-03-05",1000,8000);

create table poder(
cod_poder int primary key,
habilidade varchar(40));
insert into poder values 
(1,"Choque do trovão"),
(2,"Bola elétrica"),
(3,"Ataque rápido"),
(4,"Cauda de ferro"),
(5,"Teia Elétrica"),
(6,"Jato de Bolhas"),
(7,"Redomoinho");

create table Adquire_poder(
cod_aquisicao int primary key,
cod_jogador int,
cod_poder int,
dt_hora_aquisicao datetime,
foreign key(cod_jogador) references Jogador(cod_jogador),
foreign key(cod_poder) references Poder(cod_poder));

insert into Adquire_poder values
(1, 1, 1, '2023-06-20 19:00:00'),
(2, 1, 2, '2023-06-21 10:00:00'),
(3, 2, 1, '2023-06-21 11:00:00'),
(4, 2, 3, '2023-06-23 13:00:00'),
(5, 2, 4, '2023-06-24 15:00:00'),
(6, 3, 4, '2024-06-25 12:00:00'),
(7, 3, 5, '2024-06-26 18:00:00'),
(8, 4, 6, '2024-06-26 12:00:00');

create table Cenario(
cod_cenario int primary key,
caracteristicas varchar(30),
qtde_min_pontos int);

insert into Cenario values
(1,"Terrestre",1000),
(2,"Aquatico",2500),
(3,"Espacial",5000);

create table Partida(
cod_partida int primary key,
data_hora_inicio datetime,
data_hora_termino datetime,
pontos_obtidos int,
cod_jogador int,
cod_cenario int,
foreign key(cod_jogador) references jogador(cod_jogador),
foreign key(cod_cenario)  references cenario(cod_cenario));

insert into Partida values
(1, '2023-06-27 19:00:00', '2023-06-27 20:00:00', 300, 1, 1),
(2, '2023-06-27 19:30:00', '2023-06-27 21:00:00', 500, 2, 1),
(3, '2023-06-28 08:00:00', '2023-06-28 12:00:00', 200, 3, 1),
(4, '2023-06-28 08:30:00', '2023-06-28 11:30:00', 200, 1, 2);

-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
-- 1) Insira dados de pelo menos mais dois jogadores e pelo menos mais 10
-- partidas, colocando mais de uma partida por jogador.

insert into Jogador values
(5, 'Lucas Silva', 'lucas123', 'M', 'lucas123@gmail.com', '2002-11-21', 3000, 1500),
(6, 'Ana Paula', 'anapaula', 'F', 'anapaula@gmail.com', '2001-02-14', 4500, 2000);

insert into partida values
(5, '2023-06-29 10:00:00', '2023-06-29 11:00:00', 400, 4, 1),
(6, '2023-06-29 12:00:00', '2023-06-29 13:30:00', 350, 5, 2),
(7, '2023-06-29 15:00:00', '2023-06-29 16:00:00', 250, 6, 2),
(8, '2023-06-30 09:00:00', '2023-06-30 11:00:00', 450, 1, 3),
(9, '2023-06-30 11:30:00', '2023-06-30 13:00:00', 550, 2, 3),
(10, '2023-06-30 14:00:00', '2023-06-30 16:00:00', 500, 3, 3),
(11, '2023-07-01 08:00:00', '2023-07-01 10:00:00', 600, 4, 3),
(12, '2024-07-01 10:30:00', '2024-07-01 12:30:00', 300, 5, 1),
(13, '2024-07-01 13:00:00', '2024-07-01 15:00:00', 400, 6, 1),
(14, '2024-07-02 07:30:00', '2024-07-02 09:30:00', 450, 1, 2);

-- -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
-- 2) Utilizando junção de tabelas, gere os seguintes relatórios:
-- a) Liste o nickname do jogador,sexo,pontuação, moeda, habilidade do poder
-- e data e hora de aquisição do poder para todos os jogadores cadastrados.
-- Ordene o resultado pela data de aquisição, da mais recente para a mais antiga.
select j.nickname, j.sexo, j.pontuacao, j.moedas, aq.dt_hora_aquisicao, p.habilidade 
from adquire_poder aq join jogador j on aq.cod_jogador = j.cod_jogador
join poder p on aq.cod_poder = p.cod_poder
order by aq.dt_hora_aquisicao;

-- b) Liste o código da partida, data de início e data de término, pontos obtidos e
-- características do cenário de todas as partidas jogadas pela jogadora Roberto Carlos
select pa.cod_partida, pa.data_hora_inicio, pa.data_hora_termino, pa.pontos_obtidos, c.caracteristicas
from partida pa join cenario c on pa.cod_cenario = c.cod_cenario
join jogador j on pa.cod_jogador = j.cod_jogador
where j.nome = "Roberto Carlos";

-- c) Liste as informações dos jogadores que jogaram partidas fora do cenário Terrestre
select j.*
from partida pa join jogador j on pa.cod_jogador = j.cod_jogador
join cenario c on pa.cod_cenario = c.cod_cenario
where pa.cod_cenario != 1;

-- d) Liste o nome,sexo e ano de nascimento dos jogadores que jogaram partidas
-- no período diurno (antes das 18:00:00) no mês de junho desse ano.
select distinct j.nome, j.sexo, j.dt_nasc
from partida pa join jogador j on pa.cod_jogador = j.cod_jogador
where hour(pa.data_hora_inicio) < 18 and month(pa.data_hora_inicio) = 6 and year(pa.data_hora_inicio) = 2024;

-- e) Liste as habilidades dos poderes adquiridos pela jogadora Maria Clara no
-- mês de junho deste ano (2024).
select p.habilidade
from adquire_poder ad join jogador j on j.cod_jogador = ad.cod_jogador
join poder p on p.cod_poder = ad.cod_poder
where j.nome = "Maria Clara" and month(ad.dt_hora_aquisicao) = 6 and year(ad.dt_hora_aquisicao) = 2024;

-- 3) Utilize funções agregadas e agrupamento para atender as requisições a seguir:
-- a) Liste o nickname do jogador e a quantidade de partidas realizadas.
select j.nickname, count(*)
from partida pa join jogador j on pa.cod_jogador = j.cod_jogador
group by 1;

-- b) Liste o mês, dia e a quantidade de partidas realizadas no mês e dia,
-- considere somente partidas do ano de 2023 ou 2024.
select month(pa.data_hora_inicio), day(pa.data_hora_inicio), count(*)
from partida pa
where year(pa.data_hora_inicio) in (2023, 2024)
group by 1,2;

-- c) Liste o nome do jogador e o total de pontos obtidos pelo jogador em partidas. 
-- Considere somente jogadores que tiverem um total de pontos superior a 200.
select j.nome, sum(pa.pontos_obtidos)
from jogador j join partida pa on pa.cod_jogador = j.cod_jogador
group by 1
having sum(pa.pontos_obtidos) > 200;

-- d) Liste a característica do cenário e a quantidade de partidas realizadas no cenário.
select c.caracteristicas, count(*)
from cenario c join partida pa on pa.cod_cenario = c.cod_cenario
group by 1;

-- e) Liste o nome do jogador e a quantidade de poderes adquiridos pel jogador, 
-- ordenando em ordem decrescente.
select j.nome, count(*)
from jogador j join adquire_poder ad on ad.cod_jogador = j.cod_jogador
group by 1
order by 2 desc;

-- 4) Utilizando funções de manipulação de data, função if e concat, gere o SQL para os
-- seguintes relatórios:
-- a)Selecione o nome do jogador e a quantidade de dias decorridos desde a sua última partida.
select j.nome, timestampdiff(day, max(pa.data_hora_termino), current_date())
from jogador j join partida pa on pa.cod_jogador = j.cod_jogador
group by 1;

-- b) Selecione o nome do jogador, data da partida e o tempo gasto na partida em horas
select j.nome, date(pa.data_hora_inicio) data_partida, timestampdiff(hour, pa.data_hora_inicio, pa.data_hora_termino) tempo_gasto
from jogador j join partida pa on pa.cod_jogador = j.cod_jogador
group by 1;

-- c)Para cada um dos jogadores, listar o nickname e a média de minutos gastos em partidas.
select j.nickname, round(avg(timestampdiff(minute, pa.data_hora_inicio, pa.data_hora_termino)), 2) duracao_min
from jogador j join partida pa on pa.cod_jogador = j.cod_jogador
group by 1;

-- d)Listar o nome do jogador, sua idade e a classificação de menor de idade ou maior de idade.
select j.nome, if(timestampdiff(year, j.dt_nasc, current_date()) > 18, "maior de idade", "menor de idade") maioridade
from jogador j;

-- e)Listar o nome do jogador junto com seu nickname (ex.: Roberto Carlos - robcar), 
-- tempo gasto em partidas em minutos e um alerta de risco de vício caso o jogador tenha tempo superior a 90 minutos.
select concat(j.nome, " - ", j.nickname) nome_e_nickname, sum(timestampdiff(minute, pa.data_hora_inicio, pa.data_hora_termino)) duracao_min,
if(sum(timestampdiff(minute, pa.data_hora_inicio, pa.data_hora_termino)) > 90, "Viciado(a)", "Joga mais vai 😘") vicio
from jogador j join partida pa on pa.cod_jogador = j.cod_jogador
group by 1;