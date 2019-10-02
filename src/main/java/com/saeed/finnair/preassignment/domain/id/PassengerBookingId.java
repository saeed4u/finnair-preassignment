package com.saeed.finnair.preassignment.domain.id;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.IdClass;

@RequiredArgsConstructor
@EqualsAndHashCode
public class PassengerBookingId {

	private String passengerId;
	private String bookingId;

}
