create table staff
(
	id varchar(64) not null
		primary key,
	address varchar(100) not null,
	avatar varchar(255) not null,
	created timestamp default current_timestamp() not null,
	date_of_birth datetime not null,
	email varchar(50) not null,
	full_name varchar(50) not null,
	password varchar(64) not null,
	phone_number varchar(15) not null,
	role varchar(10) not null,
	status bit default b'1' not null,
	username varchar(32) not null
);

