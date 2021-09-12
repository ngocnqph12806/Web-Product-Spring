create table banner
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	path_image varchar(255) not null,
	path_link varchar(255) not null,
	status bit default b'1' not null,
	title varchar(100) not null
);

