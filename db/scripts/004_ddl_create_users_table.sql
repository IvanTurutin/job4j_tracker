create table if not exists j_users (
    id serial primary key,
    name varchar(2000),
    role_id int not null references j_roles(id)
);