create table history
(
	id varchar(64) not null
		primary key,
	created timestamp default current_timestamp() not null,
	details varchar(255) not null,
	id_staff_id varchar(64) null,
	constraint FK2a5pyo7mal3mte8wg30rvfbyu
		foreign key (id_staff_id) references staff (id)
);

