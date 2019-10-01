package com.saeed.finnair.preassignment.common.service;

import com.saeed.finnair.preassignment.common.dto.BookingDTO;
import com.saeed.finnair.preassignment.domain.Booking;
import com.saeed.finnair.preassignment.rest.PassengersController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class BookingAssembler extends ResourceAssemblerSupport<Booking, BookingDTO> {

	public BookingAssembler() {
		super(PassengersController.class, BookingDTO.class);
	}


	@Override
	public BookingDTO toResource(Booking entity) {

		return null;
	}
}
