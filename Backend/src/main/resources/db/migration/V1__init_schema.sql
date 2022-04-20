create table city_users
(
    id                bigserial primary key,
    username          text   not null,
    email             text   not null,
    name              text   not null,
    password          text   not null,
    phone_number      text   not null,
    type              text   not null default 'USER_GUEST'
);
create table city_locales
(
    id         bigserial primary key,
    logo_url   text,
    name       text   not null,
    num_tables bigint not null,
    type       text,
    owner_id    bigserial references city_users(id)
);
create table city_images
(
    id    bigserial primary key,
    name  text not null,
    bytes bytea,
    type  text not null
);
create table city_events
(
    id                bigserial primary key,
    adult             boolean not null,
    city              text    not null,
    covid_certificate boolean not null,
    date              DATE,
    description       text,
    logo_url          text,
    name              text    not null,
    num_reservations  bigint  not null,
    image_id          bigserial references city_images (id),
    locale_id         bigserial references city_locales (id)
);
create table city_tables
(
    id        bigserial primary key,
    reserved  boolean not null,
    locale_id bigserial references city_locales (id)
);
create table city_reservations
(
    id           bigserial primary key,
    date_time    date   not null,
    description  text,
    name         text   not null,
    phone_number bigint not null,
    locale_id    bigserial references city_locales (id),
    table_id     bigserial references city_tables (id),
    user_id      bigserial references city_users (id)
);
create table city_reviews
(
    id        bigserial primary key,
    review    text   not null,
    stars     bigint not null,
    locale_id bigserial references city_locales (id)
);
