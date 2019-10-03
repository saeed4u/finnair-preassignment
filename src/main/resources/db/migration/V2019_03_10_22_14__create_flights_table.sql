create table flights
(
  id                bigint auto_increment primary key not null,
  flight_number     varchar(10) unique not null,
  departure_airport varchar(3)         not null,
  arrival_airport   varchar(3)         not null,
  departure_date    date               not null,
  arrival_date      date               not null,
  created_at        timestamp          not null default now(),
  updated_at        timestamp                   default now()
)