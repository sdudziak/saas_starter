-- rename username field into email
alter table "user" rename column username to email;

-- add verified field to track email verification
alter table "user" add verified bool not null default false;

-- add activated information for user management purpose
alter table "user" add activated bool not null default false;

-- Create roles
insert into "role" (id, name) VALUES (2, 'ROLE_USER');
insert into "role" (id, name) VALUES (3, 'ROLE_UNVERIFIED_USER');
