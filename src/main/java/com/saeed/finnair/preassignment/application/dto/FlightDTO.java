package com.saeed.finnair.preassignment.application.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class FlightDTO {

	private String flightNumber;
	private String departureAirport;
	private String arrivalAirport;
	private LocalDate departureDate;
	private LocalDate arrivalDate;
}
