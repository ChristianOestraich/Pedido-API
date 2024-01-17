create table pedido (
      id         bigint primary key,
      número   int not null,
      data     Date not null,
      name   varchar(150) not null,
      valor    float not null,
      valor_total    float not null,
      quantidade int not null,
      codigo_cliente int not null,
      created_at timestamp default current_timestamp,
      updated_at timestamp default current_timestamp
);