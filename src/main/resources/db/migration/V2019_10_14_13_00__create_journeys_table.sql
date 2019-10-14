create table journeys
(
  id                BIGINT auto_increment primary key,
  booking_id BIGINT,
  departure_airport varchar(3),
  arrival_airport   varchar(3),
  departure_date DATE,
  arrival_date DATE,
  created_at timestamp         not null default now(),
  updated_at timestamp                  default now(),

  foreign key (booking_id) references bookings (id)
)
