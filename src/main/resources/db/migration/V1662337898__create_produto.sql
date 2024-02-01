create table produto (
   id         bigint primary key,
   nome   varchar(150) not null,
   valor      varchar(150) not null,
   categoria   varchar(150) not null,
   created_at timestamp default current_timestamp,
   updated_at timestamp default current_timestamp
);