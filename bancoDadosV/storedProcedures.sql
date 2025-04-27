	use db_ecommerce

	-- QUESTÃO 01:
	DELIMITER :D
	drop procedure if exists novo_produto;
	create procedure novo_produto(in
	nome varChar(60),
	cat_id int(11),
	preco double(10,2),
	estoque smallint(6),
	imagem varChar(100))
	BEGIN
		declare categoria_existe int;
		
		SELECT COUNT(*) INTO categoria_existe 
		FROM categorias 
		WHERE CategoriaID = cat_id;
		
		IF(cat_id > categoria_existe or cat_id <= 0) THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Erro: Categoria Inválida.';
		ELSEIF(preco <= 0) THEN
			SIGNAL SQLSTATE '45001'
			SET MESSAGE_TEXT = 'Erro: Preço menor ou igual a 0.';
		ELSE	
			insert into produtos(ProdutoNome, CategoriaID, preco, UnidadesEmEstoque, Imagem)
			values(nome, cat_id, preco, estoque, imagem);
		END IF;

	END :D
	DELIMITER ;

	CALL novo_produto('Camiseta', 8, 10, 10, 'camiseta.jpg');
	select * from produtos;

	-- QUESTÃO 02:
	DELIMITER :D
	drop procedure if exists define_prioridade;
	create procedure define_prioridade()
	BEGIN
		UPDATE clientes
		SET prioridade = 
		CASE
			WHEN pais = 'Brazil' THEN 'Alta'
			WHEN pais = 'Germany' THEN 'Media'
			ELSE 'Baixa'
		END;
			
	END :D
	DELIMITER ;

	call define_prioridade();
	select * from clientes;

	-- QUESTÃO 3:
	DELIMITER :D
	drop procedure if exists inserir_cliente;
	create procedure inserir_cliente(
		IN p_ClienteID CHAR(5),
		IN p_nome VARCHAR(30),
		IN p_cargo VARCHAR(30),
		IN p_endereco VARCHAR(60),
		IN p_cidade VARCHAR(15),
		IN p_cep VARCHAR(10),
		IN p_pais VARCHAR(15),
		IN p_telefone VARCHAR(24),
		IN p_Fax VARCHAR(24))
	BEGIN
		declare cliente_existe int;

		SELECT count(*) INTO cliente_existe 
		FROM clientes 
		WHERE ClienteID = p_ClienteID;
		
		IF(cliente_existe = 0) then 
			INSERT INTO clientes (
				ClienteID, nome, cargo, endereco, 
				cidade, cep, pais, telefone, Fax
			) VALUES (
				p_ClienteID, p_nome, p_cargo, p_endereco,
				p_cidade, p_cep, p_pais, p_telefone, p_Fax);
		ELSE
			SIGNAL SQLSTATE '45002'
			SET MESSAGE_TEXT = 'Erro: Cliente já cadastrado.';
		END IF;
	END :D
	DELIMITER ;

	CALL inserir_cliente(
		'AAAAA',                
		'João Silva',           
		'Gerente',              
		'Rua das Flores, 123', 
		'São Paulo',            
		'01234-567',            
		'Brazil',               
		'(11) 9999-8888',       
		'(11) 9999-7777'        
	);
	select * from clientes;


	-- QUESTÃO 4:
	ALTER TABLE pedidos
	ADD COLUMN codRastreio VARCHAR(20);

	DELIMITER :D
	-- drop procedure if exists gera_codigo_rastreio;
	create procedure gera_codigo_rastreio()
	BEGIN
		update pedidos
			set codRastreio =
			concat('RASTREIO-', LPAD(PedidoID, 6, '0'));
			
	END :D
	DELIMITER ;

	select * from pedidos;
	call gera_codigo_rastreio();
	select * from pedidos;
    
    -- QUESTÃO 5:
drop tables if exists logAlteracao;
create table logAlteracao (
	id_log int primary key auto_increment not null,
    campoAlterado varchar(30),
    valorAntigo varchar(100),
    valorNovo varchar(100));
    
    DELIMITER :D
drop procedure atualizar_cliente_com_auditoria;
CREATE PROCEDURE atualizar_cliente_com_auditoria(
    IN p_clienteID varchar(5), 
    IN campo varchar(30),
    IN novo_valor varchar(100))
BEGIN
    DECLARE valorAntigo VARCHAR(100);
    DECLARE sql_select  TEXT;
    DECLARE sql_update  TEXT;

    SET @sql_select = CONCAT(
        'SELECT ', campo, ' ',
        'INTO @valorAntigo ',
        'FROM clientes WHERE clienteID = ?'
    );
    PREPARE updateValorAntigo FROM @sql_select;
    SET @p1 = p_clienteID;
    EXECUTE updateValorAntigo USING @p1;
    DEALLOCATE PREPARE updateValorAntigo;

    SET valorAntigo = @valorAntigo;

    INSERT INTO logAlteracao (campoAlterado, valorAntigo, valorNovo)
    VALUES (campo, valorAntigo, novo_valor);

    SET @sql_update = CONCAT(
        'UPDATE clientes ',
        'SET ', campo, ' = ? ',
        'WHERE clienteID = ?'
    );
    PREPARE updateCliente FROM @sql_update;
    SET @p1 = novo_valor;
    SET @p2 = p_clienteID;
    EXECUTE updateCliente USING @p1, @p2;
    DEALLOCATE PREPARE updateCliente;
END :D
DELIMITER ;

select * from clientes where clienteID = 'AAAAA';
CALL atualizar_cliente_com_auditoria('AAAAA', 'fax', 'teste');
select * from logAlteracao;