package com.saeed.finnair.preassignment.application.service;

import com.saeed.finnair.preassignment.application.dto.FlightDTO;
import com.saeed.finnair.preassignment.application.dto.JourneyDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerInFlightDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerJourneyDTO;
import com.saeed.finnair.preassignment.application.exception.ResourceNotFoundException;
import com.saeed.finnair.preassignment.domain.entity.Booking;
import com.saeed.finnair.preassignment.domain.entity.Flight;
import com.saeed.finnair.preassignment.domain.entity.Journey;
import com.saeed.finnair.preassignment.domain.entity.Passenger;
import com.saeed.finnair.preassignment.domain.repo.FlightRepo;
import com.saeed.finnair.preassignment.domain.repo.PassengerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService {

	@Value("${app.carrier.code}")
	private String carrierCode;

	private final FlightRepo flightRepo;
	private final PassengerRepo passengerRepo;

	//get passengers with connecting flights

	public List<PassengerInFlightDTO> getPassengerInFlightWithDepartureDate(String flightNumber, LocalDate departureDate) {
		Flight flight = flightRepo.findByFlightNumberAndDepartureDate(flightNumber,
				departureDate).orElseThrow(() -> new ResourceNotFoundException(String.format("No flight with number %s and departure date %s was found",
				flightNumber, departureDate)));


		return flight.getJourneys().stream()
				.flatMap(journey -> {
					Booking booking = journey.getBooking();
					Set<Passenger> passengers = booking.getPassengers();
					return passengers.stream().map(passenger ->
							new PassengerInFlightDTO(passenger.getId().toString(), passenger.getFirstName(), passenger.getLastName(), booking.getBookingId())
					).collect(Collectors.toList()).stream();
				}).collect(Collectors.toList());
	}

	public PassengerJourneyDTO getPassengerFlights(Long passengerId) {
		Passenger passenger = passengerRepo.findById(passengerId).orElseThrow(() -> new ResourceNotFoundException(String.format("No passenger with ID %s was found",
				passengerId)));

		PassengerJourneyDTO passengerJourneyDTO = new PassengerJourneyDTO();
		passengerJourneyDTO.setPassengerId(passengerId.toString());
		passengerJourneyDTO.setEmail(passenger.getEmail());
		passengerJourneyDTO.setFirstName(passenger.getFirstName());
		passengerJourneyDTO.setLastName(passenger.getLastName());

		Booking booking = passenger.getBooking();
		passengerJourneyDTO.setBookingId(booking.getBookingId());

		Set<Journey> journeys = booking.getJourneys();
		List<JourneyDTO> journeyDTOS =
				journeys.stream().map(journey -> {
					List<FlightDTO> flights = journey.getFlights().stream().map(flight -> new FlightDTO(flight.getFlightNumber(), flight.getDepartureAirport(),
							flight.getArrivalAirport(), flight.getDepartureDate(), flight.getArrivalDate())).collect(Collectors.toList());
					return new JourneyDTO(journey.getDepartureAirport(), journey.getArrivalAirport(), journey.getDepartureDate(), journey.getArrivalDate(), flights);
				}).collect(Collectors.toList());

		passengerJourneyDTO.setJourneys(journeyDTOS);
		return passengerJourneyDTO;
	}

}
