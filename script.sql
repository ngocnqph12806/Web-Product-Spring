create table attribute
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    name        varchar(30)                           not null,
    status      bit       default b'1'                not null
);

create table attribute_value
(
    id              varchar(64) not null
        primary key,
    value           varchar(10) not null,
    id_attribute_id varchar(64) null,
    constraint FK736a829cwmmmo7yd9igvn1udf
        foreign key (id_attribute_id) references attribute (id)
);

create table banner
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    path_image  varchar(255)                          not null,
    path_link   varchar(255)                          not null,
    status      bit       default b'1'                not null,
    title       varchar(100)                          not null
);

create table brand
(
    id           varchar(64)                           not null
        primary key,
    address      varchar(100)                          not null,
    created      timestamp default current_timestamp() not null,
    description  varchar(255)                          null,
    email        varchar(50)                           not null,
    name         varchar(100)                          not null,
    phone_number varchar(15)                           not null,
    status       bit       default b'1'                not null
);

create table category
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    name        varchar(50)                           not null,
    status      bit       default b'1'                not null
);

create table collections
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    name        varchar(50)                           not null,
    status      bit       default b'1'                not null
);

create table collection_brand
(
    id               varchar(64) not null
        primary key,
    id_brand_id      varchar(64) null,
    id_collection_id varchar(64) null,
    constraint FK2axk8m5gsqb95h5tpbxvqs8al
        foreign key (id_brand_id) references brand (id),
    constraint FKjxb0cnyd2x4w97utvtsjxxq3o
        foreign key (id_collection_id) references collections (id)
);

create table collection_category
(
    id               varchar(64) not null
        primary key,
    id_category_id   varchar(64) null,
    id_collection_id varchar(64) null,
    constraint FKlf68ixq4c7mk00twxq9yuqgqt
        foreign key (id_category_id) references category (id),
    constraint FKnj2uo1bses5kay5ves3561tgo
        foreign key (id_collection_id) references collections (id)
);

create table product
(
    id             varchar(64)                           not null
        primary key,
    created        timestamp default current_timestamp() not null,
    description    varchar(255)                          null,
    name           varchar(100)                          not null,
    price_sale     bigint                                null,
    status         bit       default b'1'                not null,
    id_brand_id    varchar(64)                           null,
    id_category_id varchar(64)                           null,
    constraint FK7lp5ahyad52l9hbfeqbd0vw2u
        foreign key (id_brand_id) references brand (id),
    constraint FKcolce59bf4xpdvkhxwk20pwie
        foreign key (id_category_id) references category (id)
);

create table product_details
(
    id                    varchar(64)  not null
        primary key,
    location              varchar(100) not null,
    price                 bigint       null,
    quantity              int          null,
    id_attribute_value_id varchar(64)  null,
    id_product_id         varchar(64)  null,
    constraint FKk3tf6uqkrp5e8fd66lx0gfuof
        foreign key (id_attribute_value_id) references attribute_value (id),
    constraint FKpmwr7h1qm9aelsuehphbv9xs9
        foreign key (id_product_id) references product (id)
);

create table product_image
(
    id            varchar(64)  not null
        primary key,
    path          varchar(255) null,
    id_product_id varchar(64)  null,
    constraint FKq6ynwspid6roht3sivuo4jmmy
        foreign key (id_product_id) references product (id)
);

create table staff
(
    id            varchar(64)                           not null
        primary key,
    address       varchar(100)                          not null,
    avatar        varchar(255)                          not null,
    created       timestamp default current_timestamp() not null,
    date_of_birth datetime                              not null,
    email         varchar(50)                           not null,
    full_name     varchar(50)                           not null,
    password      varchar(64)                           not null,
    phone_number  varchar(15)                           not null,
    role          varchar(10)                           not null,
    status        bit       default b'1'                not null,
    username      varchar(32)                           not null
);

create table history
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    details     varchar(255)                          not null,
    id_staff_id varchar(64)                           null,
    constraint FK2a5pyo7mal3mte8wg30rvfbyu
        foreign key (id_staff_id) references staff (id)
);

create table invoice
(
    id                 varchar(64)                           not null
        primary key,
    created            timestamp default current_timestamp() not null,
    description        varchar(255)                          null,
    status             bit       default b'1'                not null,
    id_staff_check_id  varchar(64)                           null,
    id_staff_create_id varchar(64)                           null,
    constraint FK4wix7k5ijke0d0dq732v09bbv
        foreign key (id_staff_create_id) references staff (id),
    constraint FKe6dd9ksg723ssrfb61wsjf2im
        foreign key (id_staff_check_id) references staff (id)
);

create table invoice_details
(
    id                    varchar(64) not null
        primary key,
    price                 bigint      null,
    quantity              int         null,
    id_invoice_id         varchar(64) null,
    id_product_details_id varchar(64) null,
    constraint FKrnquj5foimbjyjd724dq1ug4j
        foreign key (id_product_details_id) references product_details (id),
    constraint FKs604odfky9xhw2274gbjrpc2j
        foreign key (id_invoice_id) references invoice (id)
);

create table visit
(
    id           varchar(64)                           not null
        primary key,
    address      varchar(100)                          not null,
    avatar       varchar(255)                          not null,
    created      timestamp default current_timestamp() not null,
    email        varchar(50)                           not null,
    full_name    varchar(50)                           not null,
    password     varchar(64)                           not null,
    phone_number varchar(15)                           not null,
    status       bit       default b'1'                not null
);

create table orders
(
    id              varchar(64)                           not null
        primary key,
    created         timestamp default current_timestamp() not null,
    description     varchar(255)                          null,
    payment_method  varchar(50)                           not null,
    payment_status  bit       default b'0'                null,
    status          bit       default b'1'                not null,
    id_visit_id     varchar(64)                           null,
    staff_create_id varchar(64)                           null,
    staff_sales_id  varchar(64)                           null,
    constraint FK62gy4sajyy0vwrq1be9cssdio
        foreign key (id_visit_id) references visit (id),
    constraint FK7b6itliwt6nl39154fk34w644
        foreign key (staff_sales_id) references staff (id),
    constraint FKeuiwjo2cgd52cx6rg7nhoj6f6
        foreign key (staff_create_id) references staff (id)
);

create table customer_return
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    status      bit       default b'1'                not null,
    id_order_id varchar(64)                           null,
    id_staff_id varchar(64)                           null,
    id_visit_id varchar(64)                           null,
    constraint FKaigbe57n8bsrrcmt110rsi533
        foreign key (id_visit_id) references visit (id),
    constraint FKlgum0ywn5uca8j4xkv54praws
        foreign key (id_order_id) references orders (id),
    constraint FKp1sd4otst2auiw4566gwjtujg
        foreign key (id_staff_id) references staff (id)
);

create table order_details
(
    id                    varchar(64) not null
        primary key,
    price                 bigint      null,
    price_sale            bigint      null,
    quantity              int         null,
    id_order_id           varchar(64) null,
    id_product_details_id varchar(64) null,
    constraint FKcnvgnohqc9jopl09xnw3fcqr4
        foreign key (id_order_id) references orders (id),
    constraint FKphqk0vffojho1nrlc5vn4pkpl
        foreign key (id_product_details_id) references product_details (id)
);

create table customer_return_details
(
    id                     varchar(64) not null
        primary key,
    quantity               int         null,
    id_customers_return_id varchar(64) null,
    id_order_dtails_id     varchar(64) null,
    constraint FK5wjkbhg79qdxldugr0sf84r1w
        foreign key (id_customers_return_id) references customer_return (id),
    constraint FKp0r368vlaytc19gberr07qhyc
        foreign key (id_order_dtails_id) references order_details (id)
);

create table transport
(
    id                varchar(64)                           not null
        primary key,
    address           varchar(100)                          not null,
    created           timestamp default current_timestamp() not null,
    description       varchar(255)                          null,
    full_name         varchar(50)                           not null,
    phone_number      varchar(15)                           not null,
    status            bit       default b'1'                not null,
    id_order_id       varchar(64)                           null,
    id_visit_order_id varchar(64)                           null,
    constraint FK7b7u9nq4a0k9j7x5glnwpado4
        foreign key (id_visit_order_id) references visit (id),
    constraint FKt4q0pfnwtog3qgf4dg9fl6gwe
        foreign key (id_order_id) references orders (id)
);

create table voucher
(
    id          varchar(64)                           not null
        primary key,
    code        varchar(30)                           not null,
    created     timestamp default current_timestamp() not null,
    date_end    datetime                              null,
    date_start  datetime                              null,
    description varchar(255)                          null,
    price_sale  bigint                                null,
    quantity    int                                   null,
    status      bit       default b'1'                not null,
    title       varchar(100)                          not null,
    id_staff_id varchar(64)                           null,
    constraint FKopskwqktoho0gckuvgdl2mtp3
        foreign key (id_staff_id) references staff (id)
);

create table voucher_used
(
    id            varchar(64)                           not null
        primary key,
    created       timestamp default current_timestamp() not null,
    status        bit       default b'1'                not null,
    id_visit_id   varchar(64)                           null,
    id_voucher_id varchar(64)                           null,
    constraint FKdbfd353cled6x87w32hya3qw8
        foreign key (id_visit_id) references visit (id),
    constraint FKon3qjpav0xkk7n9hkahpmgbev
        foreign key (id_voucher_id) references voucher (id)
);


