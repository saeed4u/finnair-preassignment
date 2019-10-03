package com.saeed.finnair.preassignment.application.service;

import com.saeed.finnair.preassignment.application.dto.PassengerFlightDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerInFlightDTO;
import com.saeed.finnair.preassignment.application.exception.ResourceNotFoundException;
import com.saeed.finnair.preassignment.domain.entity.Booking;
import com.saeed.finnair.preassignment.domain.entity.Flight;
import com.saeed.finnair.preassignment.domain.entity.Passenger;
import com.saeed.finnair.preassignment.domain.repo.FlightRepo;
import com.saeed.finnair.preassignment.domain.repo.PassengerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService {

	private final FlightRepo flightRepo;
	private final PassengerRepo passengerRepo;

	public List<PassengerInFlightDTO> getPassengerInFlightWithDepartureDate(String flightNumber, LocalDate departureDate) {
		Flight flight = flightRepo.findByFlightNumberAndDepartureDate(flightNumber,
				departureDate).orElseThrow(() -> new ResourceNotFoundException(String.format("No flight with number %s and departure date %s was found",
				flightNumber, departureDate)));

		return flight.getBookings().stream()
				.flatMap(booking -> {
					Set<Passenger> passengers = booking.getPassengers();
					return passengers.stream().map(passenger ->
							new PassengerInFlightDTO(passenger.getId().toString(), passenger.getFirstName(), passenger.getLastName(), booking.getBookingId())
					).collect(Collectors.toList()).stream();
				}).collect(Collectors.toList());
	}

	/*public List<PassengerFlightDTO> getPassengerFlights(Long passengerId) {
		Passenger passenger = passengerRepo.findById(passengerId).orElseThrow(() -> new ResourceNotFoundException(String.format("No passenger with ID %s was found",
				passengerId)));
		Booking booking = passenger.getBooking();

	}*/

}
