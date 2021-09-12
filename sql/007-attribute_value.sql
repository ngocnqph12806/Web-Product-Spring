create table attribute_value
(
	id varchar(64) not null
		primary key,
	value varchar(10) not null,
	id_attribute_id varchar(64) null,
	constraint FK736a829cwmmmo7yd9igvn1udf
		foreign key (id_attribute_id) references attribute (id)
);

