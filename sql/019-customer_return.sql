create table customer_return
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	status bit default b'1' not null,
	id_order_id varchar(64) null,
	id_staff_id varchar(64) null,
	id_visit_id varchar(64) null,
	constraint FKaigbe57n8bsrrcmt110rsi533
		foreign key (id_visit_id) references visit (id),
	constraint FKlgum0ywn5uca8j4xkv54praws
		foreign key (id_order_id) references orders (id),
	constraint FKp1sd4otst2auiw4566gwjtujg
		foreign key (id_staff_id) references staff (id)
);

