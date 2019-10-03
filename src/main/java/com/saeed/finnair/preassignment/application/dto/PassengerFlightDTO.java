package com.saeed.finnair.preassignment.application.dto;

import lombok.Value;

import java.util.List;

@Value
public class PassengerFlightDTO {

	private String passengerId;
	private String firstName;
	private String lastName;
	private String email;
	private String bookingId;

	private List<FlightDTO> flights;

}
