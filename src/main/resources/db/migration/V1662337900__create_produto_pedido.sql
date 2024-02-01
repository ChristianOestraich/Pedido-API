CREATE TABLE Produtos_Pedido (
     id_pedido BIGINT,
     id_produto BIGINT,
     quantidade INT NOT NULL,
     PRIMARY KEY (id_pedido, id_produto),
     FOREIGN KEY (id_pedido) REFERENCES pedido(id),
     FOREIGN KEY (id_produto) REFERENCES produto(id)
);
