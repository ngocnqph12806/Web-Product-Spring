create table order_details
(
	id varchar(64) not null
		primary key,
	price bigint null,
	price_sale bigint null,
	quantity int null,
	id_order_id varchar(64) null,
	id_product_details_id varchar(64) null,
	constraint FKcnvgnohqc9jopl09xnw3fcqr4
		foreign key (id_order_id) references orders (id),
	constraint FKphqk0vffojho1nrlc5vn4pkpl
		foreign key (id_product_details_id) references product_details (id)
);

