CREATE TABLE IF NOT EXISTS "user"
(
    id       VARCHAR(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table "user"
    add constraint UK_user_unique_username unique (username)
