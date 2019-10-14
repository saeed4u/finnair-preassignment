create table flights_journeys
(
  flight_id bigint  not null,
  journey_id   bigint  not null,
  created_at   timestamp not null default now(),
  updated_at   timestamp default now(),
  primary key (flight_id, journey_id),
  foreign key (flight_id) references flights(id),
  foreign key (journey_id) references journeys(id)
);

