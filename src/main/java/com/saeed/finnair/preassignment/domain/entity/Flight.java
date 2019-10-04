package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "flights")
@Data
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String flightNumber;

	private String departureAirport;
	private String arrivalAirport;

	@ManyToMany(mappedBy = "flights", fetch = FetchType.EAGER)
	private Set<Booking> bookings = new HashSet<>();

	private LocalDate departureDate;
	private LocalDate arrivalDate;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return Objects.equals(id, flight.id) &&
				Objects.equals(flightNumber, flight.flightNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, flightNumber);
	}

	@Override
	public String toString() {
		return flightNumber;
	}
}
