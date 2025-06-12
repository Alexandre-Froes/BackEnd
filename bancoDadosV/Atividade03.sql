-- drop database if exists db_pull_requests;
use db_pull_requests;

drop function if exists eh_ano_atual;
delimiter :)
create function eh_ano_atual(data_informada date)
returns boolean
deterministic
begin
    declare ano_info int;
    declare ano_atual int;
    set ano_info = year(data_informada);
    set ano_atual = year(curdate());
    return ano_info = ano_atual;
end;
:)
delimiter ;

select eh_ano_atual('2025-01-01');
select eh_ano_atual('2026-01-01');

drop function if exists extrair_frase;
delimiter :)
create function extrair_frase(txt text, chave varchar(50))
returns text
deterministic
begin
    declare idx int default 1;
    declare frase text;
    declare total int;
    set frase = replace(txt, '\n', '.');
    set total = length(txt) - length(replace(txt, '.', '')) + 1;
    while idx <= total do
        set frase = trim(substring_index(substring_index(txt, '.', idx), '.', -1));
        if locate(lower(chave), lower(frase)) > 0 then
            return frase;
        end if;
        set idx = idx + 1;
    end while;
    return null;
end;
:)
delimiter ;

select extrair_frase(
    'Changed performed by Carlos. This pull request improves code readability. Another unrelated comment.',
    'readability'
) as extrair_frase;

alter table pullrequests add column texto_readability text;

drop procedure if exists atualizar_readability;
delimiter :)
create procedure atualizar_readability()
begin
    declare fim int default false;
    declare dono varchar(255); 
    declare pr_id int;
    declare titulo varchar(255);
    declare corpo text;
    declare frase_extr text;
    declare cur cursor for
        select owner_repo, pr_number, title, body from pullrequests;
    declare continue handler for not found set fim = true;
    open cur;
    loop_leitura: loop
        fetch cur into dono, pr_id, titulo, corpo;
        if fim then
            leave loop_leitura;
        end if;
        set frase_extr = extrair_frase(concat(titulo, '. ', corpo), 'readability');
        update pullrequests
        set texto_readability = frase_extr
        where owner_repo = dono and pr_number = pr_id;
    end loop;
    close cur;
end;
:)
delimiter ;

call atualizar_readability();
select owner_repo, pr_number, texto_readability from pullrequests;

drop table if exists pull_request_repetida;
create table pull_request_repetida(
    id int auto_increment primary key,
    owner_repo varchar(255),
    pr_number int,
    data_tentativa timestamp
);

drop trigger if exists verificar_pullrequest_repetida;
delimiter :)
create trigger verificar_pullrequest_repetida before insert on pullrequests
for each row
begin
    declare existe int default 0;
    declare msg varchar(255);
    select count(*) into existe
    from pullrequests
    where owner_repo = new.owner_repo and pr_number = new.pr_number;
    if existe > 0 then
        insert into pull_request_repetida(owner_repo, pr_number, data_tentativa)
        values(new.owner_repo, new.pr_number, now());
        set msg = 'Inserção de pull request duplicado.';
        signal sqlstate '45000' set message_text = msg;
    end if;
end;
:)
delimiter ;

drop procedure if exists listar_pullrequests_com_participacao_insuficiente;
delimiter :)
create procedure listar_pullrequests_com_participacao_insuficiente()
begin
    declare fim int default 0;
    declare autor varchar(255);
    declare merged varchar(255);
    declare revisor varchar(255);
    declare repo varchar(255);
    declare pr_num int;
    declare total_pessoas int;
    declare a1 varchar(255);
    declare a2 varchar(255);
    declare a3 varchar(255);
    declare cur cursor for 
        select pr.owner_repo, pr.pr_number, pr.author, pr.mergedBy, rv.login 
        from pullrequests pr
        join reviews rv on pr.owner_repo = rv.owner_repo and pr.pr_number = rv.pr_number;
    declare continue handler for not found set fim = 1;
    drop temporary table if exists prs_poucos_participantes;
    create temporary table prs_poucos_participantes (
        owner_repo varchar(255),
        pr_number int,
        participantes int
    );
    open cur;
    loop_leitura: loop
        fetch cur into repo, pr_num, autor, merged, revisor;
        if fim = 1 then
            leave loop_leitura;
        end if;
        set total_pessoas = 1;
        set a1 = lower(autor);
        set a2 = lower(merged);
        set a3 = lower(revisor);
        if a1 is null and a2 is null and a3 is null then
            set total_pessoas = 0;
        elseif a1 != a2 and a2 is not null then
            set total_pessoas = total_pessoas + 1;
        end if;
        if a3 is not null and a3 not in (a1, a2) then
            set total_pessoas = total_pessoas + 1;
        end if;
        if total_pessoas < 2 then
            insert into prs_poucos_participantes(owner_repo, pr_number, participantes)
            values (repo, pr_num, total_pessoas);
        end if;
    end loop;
    close cur;
    select * from prs_poucos_participantes;
end;
:)
delimiter ;

select * from changedfiles;
select * from repositories;

delimiter :)
create trigger atualizar_changedfiles_mesma_linguagem after insert on changedfiles
for each row
begin
    declare lang varchar(255);
    select language into lang from repositories where owner_repo = new.owner_repo limit 1;
    if lang = (select language from repositories where owner_repo = new.owner_repo) then
        update pullrequests
        set changedFiles = coalesce(changedFiles, 0) + 1
        where owner_repo = new.owner_repo and pr_number = new.pr_number;
    end if;
end;
:)
delimiter ;