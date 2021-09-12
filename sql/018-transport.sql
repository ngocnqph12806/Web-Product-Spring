create table transport
(
	id varchar(64) not null
		primary key,
	address varchar(100) not null,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	full_name varchar(50) not null,
	phone_number varchar(15) not null,
	status bit default b'1' not null,
	id_order_id varchar(64) null,
	id_visit_order_id varchar(64) null,
	constraint FK7b7u9nq4a0k9j7x5glnwpado4
		foreign key (id_visit_order_id) references visit (id),
	constraint FKt4q0pfnwtog3qgf4dg9fl6gwe
		foreign key (id_order_id) references orders (id)
);

