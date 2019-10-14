package com.saeed.finnair.preassignment.application.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class JourneyDTO {

	private String departureAirport;
	private String arrivalAirport;
	private LocalDate departureDate;
	private LocalDate arrivalDate;

	private List<FlightDTO> flights;

}
