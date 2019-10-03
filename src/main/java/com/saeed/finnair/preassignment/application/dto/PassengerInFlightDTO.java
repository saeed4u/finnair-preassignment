package com.saeed.finnair.preassignment.application.dto;

import lombok.Value;

@Value
public class PassengerInFlightDTO {

	private String passengerId;
	private String firstName;
	private String lastName;
	private String bookingId;

}
