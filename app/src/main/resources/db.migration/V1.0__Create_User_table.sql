CREATE TABLE IF NOT EXISTS "user"
(
    id       VARCHAR(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    activated bool not null default false,
    verified bool not null default false,
    primary key (id)
);

alter table "user"
    add constraint unique (username)

