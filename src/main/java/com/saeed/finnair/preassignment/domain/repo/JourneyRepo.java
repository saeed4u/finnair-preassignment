package com.saeed.finnair.preassignment.domain.repo;

import com.saeed.finnair.preassignment.domain.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepo extends JpaRepository<Journey, Long> {
}
