package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "journeys")
public class Journey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String departureAirport;
	private String arrivalAirport;

	private LocalDate departureDate;
	private LocalDate arrivalDate;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@OneToOne
	private Booking booking;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "flights_journeys",
			joinColumns = {@JoinColumn(name = "journey_id")},
			inverseJoinColumns = {@JoinColumn(name = "flight_id")})
	private Set<Flight> flights = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Journey journey = (Journey) o;
		return Objects.equals(id, journey.id) &&
				Objects.equals(departureAirport, journey.departureAirport) &&
				Objects.equals(arrivalAirport, journey.arrivalAirport) &&
				Objects.equals(departureDate, journey.departureDate) &&
				Objects.equals(arrivalDate, journey.arrivalDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, departureAirport, arrivalAirport, departureDate, arrivalDate);
	}
}
