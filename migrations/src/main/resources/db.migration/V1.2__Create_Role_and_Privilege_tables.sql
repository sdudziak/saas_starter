create table IF NOT EXISTS privilege (id int8 not null, name varchar(255), primary key (id));
create table IF NOT EXISTS role (id int8 not null, name varchar(255), primary key (id));
create table IF NOT EXISTS roles_privileges (role_id int8 not null, privilege_id int8 not null);
create table IF NOT EXISTS users_roles (user_id varchar(255) not null, role_id int8 not null);

alter table roles_privileges add constraint foreign key (privilege_id) references privilege;
alter table roles_privileges add constraint foreign key (role_id) references role;
alter table users_roles add constraint foreign key (role_id) references role;
alter table users_roles add constraint foreign key (user_id) references "user";

alter table role add constraint unique (name);
alter table privilege add constraint unique (name);
