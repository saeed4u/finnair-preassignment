create table booking_passengers
(
  passenger_id bigint    not null,
  booking_id   bigint    not null,
  created_at   timestamp not null default now(),
  updated_at   timestamp          default now(),
  primary key (passenger_id, booking_id),
  foreign key (passenger_id) references passengers (id),
  foreign key (booking_id) references bookings (id)
);

