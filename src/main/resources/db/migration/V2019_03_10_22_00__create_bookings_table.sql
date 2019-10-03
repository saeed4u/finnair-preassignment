create table bookings
(
  id         bigint auto_increment primary key not null,
  booking_id varchar(6) unique not null,
  created_at timestamp         not null default now(),
  updated_at timestamp                  default now()
);
