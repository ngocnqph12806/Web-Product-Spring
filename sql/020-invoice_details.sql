create table invoice_details
(
	id varchar(64) not null
		primary key,
	price bigint null,
	quantity int null,
	id_invoice_id varchar(64) null,
	id_product_details_id varchar(64) null,
	constraint FKrnquj5foimbjyjd724dq1ug4j
		foreign key (id_product_details_id) references product_details (id),
	constraint FKs604odfky9xhw2274gbjrpc2j
		foreign key (id_invoice_id) references invoice (id)
);

