create table collection_category
(
	id varchar(64) not null
		primary key,
	id_category_id varchar(64) null,
	id_collection_id varchar(64) null,
	constraint FKlf68ixq4c7mk00twxq9yuqgqt
		foreign key (id_category_id) references category (id),
	constraint FKnj2uo1bses5kay5ves3561tgo
		foreign key (id_collection_id) references collections (id)
);

