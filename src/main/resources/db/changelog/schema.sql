create table bank (
  id NUMBER not null,
  code varchar(255) not null,
  name varchar(255) not null,
  description varchar(255),
  primary key (id)
);

create table country (
  id NUMBER not null,
  code varchar(255) not null,
  name varchar(255) not null,
  description varchar(255),
  primary key (id)
);

create table city (
  id NUMBER not null,
  code varchar(255) not null,
  name varchar(255) not null,
  description varchar(255),
  country NUMBER not null,
  primary key (id)
);


create table point (
  id NUMBER not null,
  code varchar(255) not null,
  name varchar(255) not null,
  description varchar(255),
  bank NUMBER not null,
  city NUMBER not null,
  primary key (id)
);


alter table country
add constraint UK_country_code unique (code);
alter table city
add constraint UK_city_code unique (code);
alter table bank
add constraint UK_bank_code unique (code);
alter table point
add constraint UK_point_code unique (code);

alter table city
add constraint FK_city_country
foreign key (country)
references country;

alter table point
add constraint FK_point_bank
foreign key (bank)
references bank;

alter table point
add constraint FK_city_bank
foreign key (city)
references city;

create sequence hibernate_sequence START WITH 4;