create table passengers
(
  id         bigint auto_increment primary key not null,
  first_name varchar(100)        not null,
  last_name  varchar(100)        not null default '',
  email      varchar(200) unique not null,
  booking_id bigint              not null,
  created_at timestamp           not null default now(),
  updated_at timestamp                    default now(),
  foreign key (booking_id) references bookings (id)
);
