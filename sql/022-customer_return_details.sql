create table customer_return_details
(
	id varchar(64) not null
		primary key,
	quantity int null,
	id_customers_return_id varchar(64) null,
	id_order_dtails_id varchar(64) null,
	constraint FK5wjkbhg79qdxldugr0sf84r1w
		foreign key (id_customers_return_id) references customer_return (id),
	constraint FKp0r368vlaytc19gberr07qhyc
		foreign key (id_order_dtails_id) references order_details (id)
);

