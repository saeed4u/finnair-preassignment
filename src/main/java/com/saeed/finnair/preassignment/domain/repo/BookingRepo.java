package com.saeed.finnair.preassignment.domain.repo;

import com.saeed.finnair.preassignment.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
}
