package com.saeed.finnair.preassignment.domain.repo;

import com.saeed.finnair.preassignment.domain.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger,Long> {

}
