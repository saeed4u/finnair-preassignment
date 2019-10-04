package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "passengers")
@Data
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;

	@OneToOne
	private Booking booking;

	private LocalDateTime createdAt;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Passenger passenger = (Passenger) o;
		return Objects.equals(id, passenger.id) &&
				Objects.equals(email, passenger.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email);
	}
}
