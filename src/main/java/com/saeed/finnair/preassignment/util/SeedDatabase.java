package com.saeed.finnair.preassignment.util;

import com.saeed.finnair.preassignment.domain.entity.Booking;
import com.saeed.finnair.preassignment.domain.entity.Flight;
import com.saeed.finnair.preassignment.domain.entity.Journey;
import com.saeed.finnair.preassignment.domain.entity.Passenger;
import com.saeed.finnair.preassignment.domain.repo.BookingRepo;
import com.saeed.finnair.preassignment.domain.repo.FlightRepo;
import com.saeed.finnair.preassignment.domain.repo.JourneyRepo;
import com.saeed.finnair.preassignment.domain.repo.PassengerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SeedDatabase implements CommandLineRunner {

	private final PassengerRepo passengerRepo;
	private final FlightRepo flightRepo;
	private final BookingRepo bookingRepo;
	private final JourneyRepo journeyRepo;
	private final EntityManager entityManager;

	private Passenger createRandomPassenger(Booking booking) {
		Passenger passenger = new Passenger();
		passenger.setFirstName(RandomStringUtils.random(7, true, false));
		passenger.setLastName(RandomStringUtils.random(8, true, false));
		passenger.setEmail(RandomStringUtils.random(10, true, false) + "@" + "gmail.com");
		passenger.setBooking(booking);
		passenger.setCreatedAt(LocalDateTime.now());
		return passengerRepo.save(passenger);
	}

	private Flight createRandomFlight(LocalDate departureDate, LocalDate arrivalDate) {
		Flight flight = new Flight();
		flight.setArrivalAirport(RandomStringUtils.random(3, true, false).toUpperCase());
		flight.setDepartureAirport(RandomStringUtils.random(3, true, false).toUpperCase());
		flight.setDepartureDate(departureDate);
		flight.setArrivalDate(arrivalDate);
		flight.setFlightNumber(RandomStringUtils.random(4, true, false));
		flightRepo.save(flight);
		return flight;
	}

	private void createRandomJourney(Booking booking) {
		List<Flight> flights = new ArrayList<>();

		LocalDate now = LocalDate.now();
		LocalDate tomorrow = now.plusDays(1L);
		for (int i = 0; i < 5; i++) {
			flights.add(createRandomFlight(now, tomorrow.plusDays(i)));
		}
		Flight firstFlight = flights.get(0);
		Flight lastFlight = flights.get(flights.size() - 1);
		Journey journey = new Journey();

		journey.setDepartureAirport(firstFlight.getDepartureAirport());
		journey.setArrivalAirport(lastFlight.getArrivalAirport());

		journey.setDepartureDate(firstFlight.getDepartureDate());
		journey.setArrivalDate(lastFlight.getArrivalDate());

		journey.getFlights().addAll(flights);
		journey.setBooking(booking);
		booking.getJourneys().add(journey);

		journeyRepo.save(journey);
	}

	public Booking createRandomBooking() {

		Booking booking = new Booking();
		booking.setBookingId(RandomStringUtils.random(6, true, true));
		booking.setCreatedAt(LocalDateTime.now());
		booking = bookingRepo.save(booking);

		Set<Passenger> passengers = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			passengers.add(createRandomPassenger(booking));
		}
		booking.setPassengers(passengers);
		booking = bookingRepo.save(booking);
		createRandomJourney(booking);
		return booking;
	}

	@Transactional
	public void truncateTable() {
		List<String> tableNames = new ArrayList<>();
		tableNames.add("booking_passengers");
		tableNames.add("flights");
		tableNames.add("passengers");
		tableNames.add("bookings");
		tableNames.add("journeys");
		tableNames.add("flights_journeys");
		entityManager.createNativeQuery("SET @@foreign_key_checks = 0").executeUpdate();
		tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
		entityManager.createNativeQuery("SET @@foreign_key_checks = 1").executeUpdate();

	}


	@Override
	public void run(String... args) throws Exception {
		createRandomBooking();
	}
}
