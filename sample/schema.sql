set role m3commons_s2_user;
create table department (
  id serial primary key,
  name varchar(20)
);
create table employee (
  id serial primary key,
  name varchar(20),
  department_id integer references department(id)
);
