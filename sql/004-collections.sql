create table collections
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	name varchar(50) not null,
	status bit default b'1' not null
);

