create table product
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	description varchar(255) null,
	name varchar(100) not null,
	price_sale bigint null,
	status bit default b'1' not null,
	id_brand_id varchar(64) null,
	id_category_id varchar(64) null,
	constraint FK7lp5ahyad52l9hbfeqbd0vw2u
		foreign key (id_brand_id) references brand (id),
	constraint FKcolce59bf4xpdvkhxwk20pwie
		foreign key (id_category_id) references category (id)
);

