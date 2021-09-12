create table collection_brand
(
	id varchar(64) not null
		primary key,
	id_brand_id varchar(64) null,
	id_collection_id varchar(64) null,
	constraint FK2axk8m5gsqb95h5tpbxvqs8al
		foreign key (id_brand_id) references brand (id),
	constraint FKjxb0cnyd2x4w97utvtsjxxq3o
		foreign key (id_collection_id) references collections (id)
);

