create table voucher_used
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	status bit default b'1' not null,
	id_visit_id varchar(64) null,
	id_voucher_id varchar(64) null,
	constraint FKdbfd353cled6x87w32hya3qw8
		foreign key (id_visit_id) references visit (id),
	constraint FKon3qjpav0xkk7n9hkahpmgbev
		foreign key (id_voucher_id) references voucher (id)
);

