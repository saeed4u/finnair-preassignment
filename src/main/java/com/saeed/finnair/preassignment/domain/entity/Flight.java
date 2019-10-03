package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flights")
@Data
public class Flight {

	@Id
	private Long id;

	private String flightNumber;

	private String departureAirport;
	private String arrivalAirport;

	@ManyToMany(mappedBy = "flights")
	private Set<Booking> bookings = new HashSet<>();

	private LocalDate departureDate;
	private LocalDate arrivalDate;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
