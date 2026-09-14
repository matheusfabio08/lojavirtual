DELETE FROM usuario;
DELETE FROM item_pedido;
DELETE FROM pedido;
DELETE FROM produto;
DELETE FROM cliente;
DELETE FROM categoria;

INSERT INTO categoria (id, nome, data_hora_lancamento) VALUES (1, 'Informática', NOW());
INSERT INTO categoria (id, nome, data_hora_lancamento) VALUES (2, 'Livros', NOW());

INSERT INTO cliente (id, nome, email, cpf, data_hora_atualizacao) VALUES (1, 'Ana Souza', 'ana.souza@exemplo.com', '11122233344', NOW());
INSERT INTO cliente (id, nome, email, cpf, data_hora_atualizacao) VALUES (2, 'Bruno Lima', 'bruno.lima@exemplo.com', '22233344455', NOW());

INSERT INTO produto (id, nome, preco, quantidade_em_estoque, estoque_minimo, categoria_id) VALUES (1, 'Notebook', 3500.00, 10, 3, 1);
INSERT INTO produto (id, nome, preco, quantidade_em_estoque, estoque_minimo, categoria_id) VALUES (2, 'Mouse', 59.90, 50, 20, 1);
INSERT INTO produto (id, nome, preco, quantidade_em_estoque, estoque_minimo, categoria_id) VALUES (3, 'Teclado Mecânico', 289.00, 25, 10, 1);
INSERT INTO produto (id, nome, preco, quantidade_em_estoque, estoque_minimo, categoria_id) VALUES (4, 'Clean Code', 120.00, 15, 20, 2);

INSERT INTO pedido (id, cliente_id, data_pedido) VALUES (1, 1, '2026-08-20 10:00:00');
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (1, 1, 1, 1, 3500.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (2, 1, 2, 2, 59.90);

INSERT INTO usuario (id, username, senha, habilitado) VALUES (1, 'admin', '$2b$10$puZInsGGKg40U2lnBdV6FunCRhot8a2O2mrznuWtgMx8kvIXMqjFu', true);
