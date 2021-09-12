create table brand
(
	id varchar(64) not null
		primary key,
	address varchar(100) not null,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	email varchar(50) not null,
	name varchar(100) not null,
	phone_number varchar(15) not null,
	status bit default b'1' not null
);

