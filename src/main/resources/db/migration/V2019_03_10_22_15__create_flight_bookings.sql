create table flight_bookings
(
  flight_id bigint  not null,
  booking_id   bigint  not null,
  created_at   timestamp not null default now(),
  updated_at   timestamp default now(),
  primary key (flight_id, booking_id),
  foreign key (flight_id) references flights(id),
  foreign key (booking_id) references bookings(id)
);

