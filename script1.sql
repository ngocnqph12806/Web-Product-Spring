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
    id          varchar(64)                              not null
        primary key,
    created     timestamp    default current_timestamp() not null,
    description varchar(255)                             null,
    name        varchar(50)                              not null,
    status      bit          default b'1'                not null,
    banner      varchar(255)                             not null,
    path_url    varchar(100) default 'path-url'          not null,
    id_url      int auto_increment,
    constraint category_id_url_uindex
        unique (id_url)
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
    id               varchar(64)                           not null
        primary key,
    created          timestamp default current_timestamp() not null,
    name             varchar(100)                          not null,
    price_sale       bigint    default 0                   not null,
    status           bit       default b'1'                not null,
    id_brand_id      varchar(64)                           not null,
    id_category_id   varchar(64)                           not null,
    price            bigint    default 0                   not null,
    quantity         int       default 0                   not null,
    location         varchar(255)                          not null,
    path_url         varchar(150)                          not null,
    id_url           int auto_increment,
    description      varchar(255)                          not null,
    path_user_manual varchar(255)                          null,
    category_details varchar(50)                           not null,
    color            varchar(20)                           not null,
    constraint product_id_url_uindex
        unique (id_url),
    constraint FK7lp5ahyad52l9hbfeqbd0vw2u
        foreign key (id_brand_id) references brand (id),
    constraint FKcolce59bf4xpdvkhxwk20pwie
        foreign key (id_category_id) references category (id)
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
    date_of_birth date                                  not null,
    email         varchar(50)                           not null,
    full_name     varchar(50)                           not null,
    password      varchar(64)                           null,
    phone_number  varchar(15)                           not null,
    role          varchar(15)                           not null,
    status        bit       default b'0'                not null,
    username      varchar(32)                           not null,
    block         bit       default b'0'                not null,
    constraint staff_email_uindex
        unique (email),
    constraint staff_phone_number_uindex
        unique (phone_number),
    constraint staff_username_uindex
        unique (username)
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
    id            varchar(64) not null
        primary key,
    price         bigint      null,
    quantity      int         null,
    id_invoice_id varchar(64) null,
    id_product    varchar(64) null,
    id_product_id varchar(64) null,
    constraint FK85s620pjmhp5artywj731h062
        foreign key (id_product_id) references product (id),
    constraint FKrnquj5foimbjyjd724dq1ug4j
        foreign key (id_product) references product (id),
    constraint FKs604odfky9xhw2274gbjrpc2j
        foreign key (id_invoice_id) references invoice (id)
);

create table value_details
(
    id   varchar(64) not null
        primary key,
    name varchar(50) not null
);

create table product_details
(
    id                  varchar(64)  not null
        primary key,
    description         varchar(255) not null,
    title               varchar(150) not null,
    id_product_id       varchar(64)  null,
    id_value_details_id varchar(64)  null,
    path_banner         varchar(255) null,
    constraint FKkay5ru40ooiiaugbpjtaubpds
        foreign key (id_value_details_id) references value_details (id),
    constraint FKpmwr7h1qm9aelsuehphbv9xs9
        foreign key (id_product_id) references product (id)
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
    id            varchar(64) not null
        primary key,
    price         bigint      null,
    price_sale    bigint      null,
    quantity      int         null,
    id_order_id   varchar(64) null,
    id_product    varchar(64) null,
    id_product_id varchar(64) null,
    constraint FKcnvgnohqc9jopl09xnw3fcqr4
        foreign key (id_order_id) references orders (id),
    constraint FKo5js6n5msi8xjnoij06gnta4d
        foreign key (id_product_id) references product (id),
    constraint FKphqk0vffojho1nrlc5vn4pkpl
        foreign key (id_product) references product (id)
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

create table review_product
(
    id            varchar(64)                           not null
        primary key,
    created       timestamp default current_timestamp() not null,
    description   varchar(255)                          not null,
    introduce     bit       default b'0'                not null,
    status        bit       default b'1'                not null,
    id_product_id varchar(64)                           not null,
    point         int(3)    default 0                   not null,
    id_visit_id   varchar(64)                           not null,
    constraint review_product_id_visit_id_id_product_id_index
        unique (id_visit_id, id_product_id),
    constraint FKlr9muijnmi87guk7k7s0481je
        foreign key (id_visit_id) references visit (id),
    constraint FKn09y179hon4wrursj28sg415x
        foreign key (id_product_id) references product (id)
);

create table review_image
(
    id                   varchar(64)  not null
        primary key,
    path_image           varchar(255) not null,
    id_review_product_id varchar(64)  null,
    constraint FKgxttplepe18qci2ymim20lp59
        foreign key (id_review_product_id) references review_product (id)
);

create index review_product_id_visit_id_index
    on review_product (id_visit_id);

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


