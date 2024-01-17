create table users (
    id         bigint primary key,
    username   varchar(150) not null,
    email      varchar(150) not null,
    password   varchar(150) not null,
    address    varchar(200) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);