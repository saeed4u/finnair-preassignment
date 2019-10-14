package com.saeed.finnair.preassignment.application.dto;

import com.saeed.finnair.preassignment.domain.entity.Journey;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class PassengerJourneyDTO {
	private String passengerId;
	private String firstName;
	private String lastName;
	private String email;
	private String bookingId;


	private List<JourneyDTO> journeys;

}
