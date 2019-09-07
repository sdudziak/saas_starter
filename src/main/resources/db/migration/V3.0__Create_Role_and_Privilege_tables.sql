create table IF NOT EXISTS privilege (id int8 not null, name varchar(255), primary key (id));
create table IF NOT EXISTS role (id int8 not null, name varchar(255), primary key (id));
create table IF NOT EXISTS roles_privileges (role_id int8 not null, privilege_id int8 not null);
create table IF NOT EXISTS users_roles (user_id varchar(255) not null, role_id int8 not null);

alter table roles_privileges add constraint fk_roles_privileges_privilege_id_to_privilege foreign key (privilege_id) references privilege;
alter table roles_privileges add constraint fk_roles_privileges_role_id_to_role foreign key (role_id) references role;
alter table users_roles add constraint fk_users_roles_role_id_to_role foreign key (role_id) references role;
alter table users_roles add constraint fk_users_roles_user_id_to_user foreign key (user_id) references "user";

alter table role add constraint UQ_role_unique_name unique (name);
alter table privilege add constraint UQ_role_unique_name unique (name);
