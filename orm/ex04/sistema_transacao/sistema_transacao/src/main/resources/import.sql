-- Inserção de clientes
INSERT INTO clientes (nome, conta) VALUES ('Ana Paula Silva', 1500.75);
INSERT INTO clientes (nome, conta) VALUES ('Roberto Carlos Souza', 3200.00);
INSERT INTO clientes (nome, conta) VALUES ('Maria José Santos', 750.50);

-- Inserção de produtos
INSERT INTO produtos (nome, preco, estoque) VALUES ('Teclado Mecânico RGB', 350.00, 150);
INSERT INTO produtos (nome, preco, estoque) VALUES ('Mouse Gamer Wireless', 220.50, 80);
INSERT INTO produtos (nome, preco, estoque) VALUES ('Monitor 24 polegadas', 899.99, 25);

-- Inserção de pedidos
INSERT INTO pedidos (preco_venda, quantidade, produto_id, cliente_id, status) VALUES (350.00, 2, 1, 1, 'CONCLUIDO');
INSERT INTO pedidos (preco_venda, quantidade, produto_id, cliente_id, status) VALUES (220.50, 1, 2, 2, 'CONCLUIDO');
INSERT INTO pedidos (preco_venda, quantidade, produto_id, cliente_id, status) VALUES (899.99, 1, 3, 3, 'PENDENTE');