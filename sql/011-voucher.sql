create table voucher
(
	id varchar(64) not null
		primary key,
	code varchar(30) not null,
	created timestamp default current_timestamp() not null,
	date_end datetime null,
	date_start datetime null,
	description varchar(255) null,
	price_sale bigint null,
	quantity int null,
	status bit default b'1' not null,
	title varchar(100) not null,
	id_staff_id varchar(64) null,
	constraint FKopskwqktoho0gckuvgdl2mtp3
		foreign key (id_staff_id) references staff (id)
);

