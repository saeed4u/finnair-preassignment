package com.saeed.finnair.preassignment.domain.repo;

import com.saeed.finnair.preassignment.domain.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {

	Optional<Flight> findByFlightNumberAndDepartureDate(String flightNumber, LocalDate departureDate);

}
