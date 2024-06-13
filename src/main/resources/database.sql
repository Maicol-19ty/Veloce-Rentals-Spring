create table users
(
    user_id    int auto_increment
        primary key,
    username   varchar(255)                       not null,
    password   varchar(255)                       not null,
    email      varchar(255)                       not null,
    full_name  varchar(255)                       not null,
    created_at datetime default CURRENT_TIMESTAMP null,
    last_login datetime                           null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table vehicles
(
    vehicle_id    int auto_increment
        primary key,
    type          enum ('car', 'motorcycle')                                      not null,
    make          varchar(255)                                                    not null,
    model         varchar(255)                                                    not null,
    year          int                                                             not null,
    price_per_day decimal(10, 2)                                                  not null,
    status        enum ('available', 'rented', 'maintenance') default 'available' not null
);

create table reservations
(
    reservation_id int auto_increment
        primary key,
    user_id        int            not null,
    vehicle_id     int            not null,
    start_date     date           not null,
    end_date       date           not null,
    total_cost     decimal(10, 2) not null,
    constraint reservations_ibfk_1
        foreign key (user_id) references users (user_id),
    constraint reservations_ibfk_2
        foreign key (vehicle_id) references vehicles (vehicle_id)
);

create index user_id
    on reservations (user_id);

create index vehicle_id
    on reservations (vehicle_id);

create table transactions
(
    transaction_id   int auto_increment
        primary key,
    reservation_id   int                                        not null,
    amount           decimal(10, 2)                             not null,
    transaction_date datetime default CURRENT_TIMESTAMP         null,
    payment_method   enum ('credit card', 'debit card', 'cash') not null,
    constraint transactions_ibfk_1
        foreign key (reservation_id) references reservations (reservation_id)
);

create index reservation_id
    on transactions (reservation_id);