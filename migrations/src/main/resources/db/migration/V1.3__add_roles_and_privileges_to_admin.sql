-- empty data first
truncate table "users_roles" cascade;
truncate table "roles_privileges" cascade;
truncate table "role" cascade;
truncate table "privilege" cascade;

-- Create privileges
insert into "privilege" (id, name)
VALUES (1, 'READ_USERS_DATA_PRIVILEGE');
insert into "privilege" (id, name)
VALUES (2, 'WRITE_USERS_DATA_PRIVILEGE');
insert into "privilege" (id, name)
VALUES (3, 'READ_PRIVILEGES_PRIVILEGE');
insert into "privilege" (id, name)
VALUES (4, 'WRITE_PRIVILEGES_PRIVILEGE');
insert into "privilege" (id, name)
VALUES (5, 'READ_ROLES_PRIVILEGE');
insert into "privilege" (id, name)
VALUES (6, 'WRITE_ROLES_PRIVILEGE');

-- Create roles
insert into "role" (id, name)
VALUES (1, 'ROLE_ADMIN');
insert into "role" (id, name)
VALUES (2, 'ROLE_USER');
insert into "role" (id, name)
VALUES (3, 'ROLE_UNVERIFIED_USER');

-- link privileges to the role admin
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 1);
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 2);
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 3);
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 4);
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 5);
insert into "roles_privileges"(role_id, privilege_id)
VALUES (1, 6);

-- link role_admin with admin user
insert into "users_roles"(user_id, role_id)
VALUES (1, 1);
