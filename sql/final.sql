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
    status       bit       default b'1'                not null,
    constraint UK_505xtsx85qyo70ukq3fktug2p
        unique (phone_number),
    constraint UK_rdxh7tq2xs66r485cc8dkxt77
        unique (name),
    constraint UK_tly6e6jmqmqtsqaygqg6ak4nd
        unique (email)
);

create table category
(
    id          varchar(64)                           not null
        primary key,
    banner      varchar(255)                          not null,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    id_url      bigint                                not null,
    name        varchar(50)                           not null,
    path_url    varchar(100)                          not null,
    status      bit       default b'1'                not null
);

create table collections
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          null,
    id_url      bigint                                not null,
    name        varchar(50)                           not null,
    path_url    varchar(255)                          not null,
    status      bit       default b'1'                not null,
    constraint UK_4tcw8g8cvybqr4vlujxbwiqm7
        unique (id_url),
    constraint UK_jh61vsqk1x2qap6tjh8aurcd7
        unique (path_url)
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
    category_details varchar(100)                          not null,
    color            varchar(20)                           not null,
    created          timestamp default current_timestamp() not null,
    description      text                                  not null,
    id_url           bigint                                not null,
    location         varchar(255)                          not null,
    name             varchar(100)                          not null,
    path_url         varchar(255)                          not null,
    path_user_manual varchar(255)                          not null,
    price            bigint                                null,
    price_sale       bigint                                null,
    quantity         int                                   null,
    status           bit       default b'1'                not null,
    id_brand_id      varchar(64)                           null,
    id_category_id   varchar(64)                           null,
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

create table users
(
    id            varchar(64)                           not null
        primary key,
    address       varchar(100)                          not null,
    avatar        varchar(255)                          not null,
    block         bit       default b'0'                not null,
    created       timestamp default current_timestamp() not null,
    date_of_birth datetime                              not null,
    email         varchar(50)                           not null,
    full_name     varchar(50)                           not null,
    password      varchar(64)                           null,
    phone_number  varchar(15)                           not null,
    role          varchar(10)                           not null,
    status        bit       default b'0'                not null,
    username      varchar(32)                           not null,
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UK_9q63snka3mdh91as4io72espi
        unique (phone_number),
    constraint UK_r43af9ap4edm43mmtq01oddj6
        unique (username)
);

create table history
(
    id          varchar(64)                           not null
        primary key,
    created     timestamp default current_timestamp() not null,
    description varchar(255)                          not null,
    details     text                                  not null,
    id_staff_id varchar(64)                           null,
    constraint FKjidgd967cam45xdma59vpeaqt
        foreign key (id_staff_id) references users (id)
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
    constraint FK9v1e9udi6g2kg4ovssyeb88ys
        foreign key (id_staff_create_id) references users (id),
    constraint FKnk0bmvdpuip5kbw4cq9ttjn19
        foreign key (id_staff_check_id) references users (id)
);

create table invoice_details
(
    id            varchar(64) not null
        primary key,
    price         bigint      null,
    quantity      int         null,
    id_invoice_id varchar(64) null,
    id_product_id varchar(64) null,
    constraint FK85s620pjmhp5artywj731h062
        foreign key (id_product_id) references product (id),
    constraint FKs604odfky9xhw2274gbjrpc2j
        foreign key (id_invoice_id) references invoice (id)
);

create table review_product
(
    id            varchar(64)                           not null
        primary key,
    created       timestamp default current_timestamp() not null,
    description   varchar(255)                          not null,
    introduce     bit       default b'0'                not null,
    point         int                                   null,
    status        bit       default b'1'                not null,
    id_product_id varchar(64)                           null,
    id_visit_id   varchar(64)                           null,
    constraint review_product_id_visit_id_id_product_id_uindex
        unique (id_visit_id, id_product_id),
    constraint FKbmdvp1dfkgua955tc3o0xh9vv
        foreign key (id_visit_id) references users (id),
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
    path_banner         varchar(150) not null,
    title               varchar(150) not null,
    id_product_id       varchar(64)  null,
    id_value_details_id varchar(64)  null,
    constraint FKkay5ru40ooiiaugbpjtaubpds
        foreign key (id_value_details_id) references value_details (id),
    constraint FKpmwr7h1qm9aelsuehphbv9xs9
        foreign key (id_product_id) references product (id)
);

create table voucher
(
    id          varchar(64)                           not null
        primary key,
    code        varchar(30)                           not null,
    created     timestamp default current_timestamp() not null,
    date_end    datetime                              null,
    date_start  datetime                              null,
    description text                                  not null,
    price_sale  bigint                                null,
    quantity    int                                   null,
    status      bit       default b'1'                not null,
    title       varchar(100)                          not null,
    id_staff_id varchar(64)                           null,
    constraint FK3xd64y5sv4i1gmceg1wxwde86
        foreign key (id_staff_id) references users (id)
);

create table orders
(
    id              varchar(64)                           not null
        primary key,
    city            varchar(50)                           not null,
    created         timestamp default current_timestamp() not null,
    description     varchar(255)                          null,
    district        varchar(50)                           not null,
    email           varchar(50)                           not null,
    full_name       varchar(50)                           not null,
    note            varchar(255)                          null,
    payment_method  varchar(50)                           not null,
    payment_status  bit       default b'0'                null,
    phone_number    varchar(15)                           not null,
    status          bit       default b'1'                not null,
    village         varchar(50)                           not null,
    ward            varchar(50)                           not null,
    id_visit_id     varchar(64)                           null,
    id_voucher_id   varchar(64)                           null,
    staff_create_id varchar(64)                           null,
    staff_sales_id  varchar(64)                           null,
    constraint FK1lnpng39ji7hijga6j4nr5vbj
        foreign key (staff_create_id) references users (id),
    constraint FK3qn6tdwkqd0d2el8b4k92cqwl
        foreign key (id_voucher_id) references voucher (id),
    constraint FKfbpao09xbyep1b27ee7t3axci
        foreign key (staff_sales_id) references users (id),
    constraint FKos6u71fs6glo4ds3osi083akn
        foreign key (id_visit_id) references users (id)
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
    constraint FK3tmvykhm3a9qi2b22e2ai7fyu
        foreign key (id_staff_id) references users (id),
    constraint FKlgum0ywn5uca8j4xkv54praws
        foreign key (id_order_id) references orders (id)
);

create table order_details
(
    id            varchar(64)   not null
        primary key,
    price         bigint        not null,
    price_sale    int default 0 not null,
    quantity      int           not null,
    id_order_id   varchar(64)   null,
    id_product_id varchar(64)   null,
    constraint FKcnvgnohqc9jopl09xnw3fcqr4
        foreign key (id_order_id) references orders (id),
    constraint FKo5js6n5msi8xjnoij06gnta4d
        foreign key (id_product_id) references product (id)
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
    id               varchar(64)                           not null
        primary key,
    created          timestamp default current_timestamp() not null,
    description      varchar(255)                          null,
    status_transport varchar(255)                          not null,
    id_order_id      varchar(64)                           null,
    constraint FKt4q0pfnwtog3qgf4dg9fl6gwe
        foreign key (id_order_id) references orders (id)
);

create table voucher_used
(
    id            varchar(64)                           not null
        primary key,
    created       timestamp default current_timestamp() not null,
    status        bit       default b'1'                not null,
    id_visit_id   varchar(64)                           null,
    id_voucher_id varchar(64)                           null,
    constraint FK3uvr67ebkhrogfchpjkdi0m0l
        foreign key (id_visit_id) references users (id),
    constraint FKon3qjpav0xkk7n9hkahpmgbev
        foreign key (id_voucher_id) references voucher (id)
);

create table wishlist
(
    id            varchar(64) not null
        primary key,
    id_product_id varchar(64) null,
    id_visit_id   varchar(64) null,
    constraint FK7fhmeuhimryeqlec2eyvk54f5
        foreign key (id_visit_id) references users (id),
    constraint FKlfj73rvkgw2h2s7ic9lchamrw
        foreign key (id_product_id) references product (id)
);


