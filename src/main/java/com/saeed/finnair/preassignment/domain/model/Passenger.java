package com.saeed.finnair.preassignment.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class Passenger {

	@Id
	private String id;

	private final String firstName;
	private final String lastName;
	@Column(unique = true)
	private final String email;

	private LocalDateTime createdAt = LocalDateTime.now();


}
