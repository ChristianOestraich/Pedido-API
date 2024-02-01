ALTER TABLE pedido ADD COLUMN id_user BIGINT,
ADD CONSTRAINT fk_pedido_user FOREIGN KEY (id_user) REFERENCES users(id);