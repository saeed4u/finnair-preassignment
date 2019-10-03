package com.saeed.finnair.preassignment.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "passengers")
@Data
public class Passenger {

	@Id
	private Long id;

	private String firstName;
	private String lastName;
	private String email;

	@OneToOne
	private Booking booking;

	private LocalDateTime createdAt;


}
