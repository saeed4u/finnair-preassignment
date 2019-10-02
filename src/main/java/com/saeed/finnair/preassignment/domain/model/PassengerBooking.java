package com.saeed.finnair.preassignment.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class PassengerBooking {

	@Id
	private String passengerId;

	@Id
	private String bookingId;

	@OneToOne
	private Passenger passenger;

	@OneToOne
	private Booking booking;

}
