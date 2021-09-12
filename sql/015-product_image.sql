create table product_image
(
	id varchar(64) not null
		primary key,
	path varchar(255) null,
	id_product_id varchar(64) null,
	constraint FKq6ynwspid6roht3sivuo4jmmy
		foreign key (id_product_id) references product (id)
);

