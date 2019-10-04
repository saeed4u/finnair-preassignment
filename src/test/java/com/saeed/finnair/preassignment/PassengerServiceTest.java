package com.saeed.finnair.preassignment;


import com.saeed.finnair.preassignment.application.dto.PassengerFlightDTO;
import com.saeed.finnair.preassignment.application.dto.PassengerInFlightDTO;
import com.saeed.finnair.preassignment.application.exception.ResourceNotFoundException;
import com.saeed.finnair.preassignment.application.service.PassengerService;
import com.saeed.finnair.preassignment.domain.entity.Booking;
import com.saeed.finnair.preassignment.domain.entity.Flight;
import com.saeed.finnair.preassignment.util.SeedDatabase;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
		PassengerFlightDTO passengerFlights = passengerService.getPassengerFlights(1L);
		Assertions.assertThat(passengerFlights).isNotNull();
		Assertions.assertThat(passengerFlights.getFlights()).isNotEmpty();
		Assertions.assertThat(passengerFlights.getBookingId()).isNotEmpty();
	}

	@Test
	public void test_get_passengers_expect_error() {
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> passengerService.getPassengerFlights(0L));
	}


	@Test
	public void test_get_passengers_in_flight_expect_success() {
		Flight flight = (Flight) booking.getFlights().toArray()[0];
		String flightNumber = flight.toString();
		List<PassengerInFlightDTO> passengerInFlightDTO = passengerService.getPassengerInFlightWithDepartureDate(flightNumber, flight.getDepartureDate());
		Assertions.assertThat(passengerInFlightDTO).isNotEmpty();
	}

	@Test
	public void test_get_passengers_in_flight_expect_error() {
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> passengerService.getPassengerInFlightWithDepartureDate("invalid_flight", LocalDate.now()));
	}


}
