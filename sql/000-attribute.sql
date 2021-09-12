create table attribute
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	name varchar(30) not null,
	status bit default b'1' not null
);

