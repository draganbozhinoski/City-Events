create table city_locales(
    id bigserial primary key,
    logo_url text,
    name text not null,
    num_tables bigint not null,
    type text
);
create table city_events(
    id bigserial primary key,
    adult boolean not null,
    city text not null,
    covid_certificate boolean not null,
    date DATE,
    description text,
    logo_url text,
    name text not null,
    num_reservations bigint not null,
    locale_id bigserial references city_locales(id)
);
create table city_tables(
                            id bigserial primary key,
                            reserved boolean not null,
                            locale_id bigserial references city_locales(id)
);
create table city_users(
                           username text primary key,
                           email text not null,
                           name text not null,
                           password text not null,
                           phone_number bigint not null,
                           type text not null,
                           locale_manages_id bigserial references city_locales(id)
);
create table city_reservations(
    id bigserial primary key,
    date_time date not null,
    description text,
    name text not null,
    phone_number bigint not null,
    locale_id bigserial references city_locales(id),
    table_id bigserial references city_tables(id),
    user_username text references city_users(username)
);
create table city_reviews(
    id bigserial primary key,
    review text not null,
    stars bigint not null,
    locale_id bigserial references city_locales(id)
);