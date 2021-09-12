create table invoice
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	status bit default b'1' not null,
	id_staff_check_id varchar(64) null,
	id_staff_create_id varchar(64) null,
	constraint FK4wix7k5ijke0d0dq732v09bbv
		foreign key (id_staff_create_id) references staff (id),
	constraint FKe6dd9ksg723ssrfb61wsjf2im
		foreign key (id_staff_check_id) references staff (id)
);

