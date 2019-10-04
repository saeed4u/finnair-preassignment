package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity()
@Data
@Table(name = "bookings")
public class Booking {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String bookingId;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "booking_passengers",
			joinColumns = {@JoinColumn(name = "booking_id")},
			inverseJoinColumns = {@JoinColumn(name = "passenger_id")}
	)
	private Set<Passenger> passengers = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "flight_bookings",
			joinColumns = {@JoinColumn(name = "booking_id")},
			inverseJoinColumns = {@JoinColumn(name = "flight_id")}
	)
	private Set<Flight> flights = new HashSet<>();

	private LocalDateTime createdAt;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Booking booking = (Booking) o;
		return Objects.equals(id, booking.id) &&
				Objects.equals(bookingId, booking.bookingId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, bookingId);
	}
}
