package com.saeed.finnair.preassignment.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

	@Id
	private String id;

	private

	private LocalDateTime createdAt;


}
