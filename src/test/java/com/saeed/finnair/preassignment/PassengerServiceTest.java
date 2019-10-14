package com.saeed.finnair.preassignment;


import com.saeed.finnair.preassignment.application.dto.JourneyDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerInFlightDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerJourneyDTO;
import com.saeed.finnair.preassignment.application.exception.ResourceNotFoundException;
import com.saeed.finnair.preassignment.application.service.PassengerService;
import com.saeed.finnair.preassignment.domain.entity.Booking;
import com.saeed.finnair.preassignment.domain.entity.Flight;
import com.saeed.finnair.preassignment.domain.entity.Journey;
import com.saeed.finnair.preassignment.util.SeedDatabase;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class PassengerServiceTest {

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private SeedDatabase seedDatabase;

	private Booking booking;

	@Before
	public void setUp() {
		booking = seedDatabase.createRandomBooking();
	}

	@After
	public void truncateTables() {
		seedDatabase.truncateTable();
	}

	@Test
	public void test_get_passengers_expect_success() {
		PassengerJourneyDTO passengerFlights = passengerService.getPassengerFlights(1L);
		Assertions.assertThat(passengerFlights).isNotNull();
		List<JourneyDTO> flightDTOS = passengerFlights.getJourneys();
		Assertions.assertThat(flightDTOS).isNotEmpty();
		Assertions.assertThat(passengerFlights.getBookingId()).isNotEmpty();
	}

	@Test
	public void test_get_passengers_expect_error() {
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> passengerService.getPassengerFlights(0L));
	}


	@Test
	public void test_get_passengers_in_flight_expect_success() {
		log.info("Booking {}",booking);
		Set<Journey> journeys = booking.getJourneys();
		Assertions.assertThat(journeys).isNotEmpty();
		Journey journey = (Journey) journeys.toArray()[0];

		Set<Flight> flights = journey.getFlights();
		Assertions.assertThat(flights).isNotEmpty();
		Flight flight = (Flight) flights.toArray()[0];
		List<PassengerInFlightDTO> passengerInFlightDTO = passengerService.getPassengerInFlightWithDepartureDate(flight.getFlightNumber(), flight.getDepartureDate());
		Assertions.assertThat(passengerInFlightDTO).isNotEmpty();
	}

	@Test
	public void test_get_passengers_in_flight_expect_error() {
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> passengerService.getPassengerInFlightWithDepartureDate("invalid_flight", LocalDate.now()));
	}


}
