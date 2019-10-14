package com.saeed.finnair.preassignment.rest;

import com.saeed.finnair.preassignment.application.service.PassengerService;
import com.saeed.finnair.preassignment.domain.model.ApiError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
@Slf4j
public class PassengersController {


	private final PassengerService passengerService;

	//   Passengers in the same flight who have the same booking

	/**
	 *
	 * Passenger a,b,c
	 * flight a
	 * passenger a,b
	 * flight b
	 *
	 *
	 */


	@GetMapping
	public ResponseEntity getPassengerInFlightDTO(@RequestParam("flightNumber") String flightNumber,
												  @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate departureDate) {

		log.info("New passenger in flight request. Params flightNumber:{} departureDate:{}", flightNumber, departureDate);

		if (flightNumber == null || flightNumber.isEmpty()) {
			return new ResponseEntity<>(new ApiError("flightNumber is required"), HttpStatus.BAD_REQUEST);
		}

		if (departureDate == null) {
			return new ResponseEntity<>(new ApiError("departureDate is required"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(passengerService.getPassengerInFlightWithDepartureDate(flightNumber, departureDate), HttpStatus.OK);
	}


	//flights can belong to a journey.

	@GetMapping("/{passengerId}")
	public ResponseEntity getPassengerFlights(@PathVariable("passengerId") Long passengerId) {
		log.info("New passenger flights request. passengerId: {}", passengerId);

		if (passengerId == null) {
			return new ResponseEntity<>(new ApiError("passengerId is required"), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(passengerService.getPassengerFlights(passengerId), HttpStatus.OK);
	}


}
