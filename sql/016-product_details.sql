create table product_details
(
	id varchar(64) not null
		primary key,
	location varchar(100) not null,
	price bigint null,
	quantity int null,
	id_attribute_value_id varchar(64) null,
	id_product_id varchar(64) null,
	constraint FKk3tf6uqkrp5e8fd66lx0gfuof
		foreign key (id_attribute_value_id) references attribute_value (id),
	constraint FKpmwr7h1qm9aelsuehphbv9xs9
		foreign key (id_product_id) references product (id)
);

