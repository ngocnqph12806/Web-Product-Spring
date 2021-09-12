create table orders
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	payment_method varchar(50) not null,
	payment_status bit default b'0' null,
	status bit default b'1' not null,
	id_visit_id varchar(64) null,
	staff_create_id varchar(64) null,
	staff_sales_id varchar(64) null,
	constraint FK62gy4sajyy0vwrq1be9cssdio
		foreign key (id_visit_id) references visit (id),
	constraint FK7b6itliwt6nl39154fk34w644
		foreign key (staff_sales_id) references staff (id),
	constraint FKeuiwjo2cgd52cx6rg7nhoj6f6
		foreign key (staff_create_id) references staff (id)
);

