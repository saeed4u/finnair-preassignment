package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Data
@Table(name = "bookings")
public class Booking {

	@Id
	private Long id;

	private String bookingId;

	@OneToMany
	@JoinTable(
			name = "passenger_bookings",
			joinColumns = {@JoinColumn(name = "booking_id")},
			inverseJoinColumns = {@JoinColumn(name = "passenger_id")}
	)
	private Set<Passenger> passengers = new HashSet<>();

	@ManyToMany
	@JoinTable(
			name = "flight_bookings",
			joinColumns = {@JoinColumn(name = "booking_id")},
			inverseJoinColumns = {@JoinColumn(name = "flight_id")}
	)
	private Set<Flight> flights = new HashSet<>();

	private LocalDateTime createdAt;

}
