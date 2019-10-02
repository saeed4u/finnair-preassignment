package com.saeed.finnair.preassignment.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Flight {

	@Id
	private String flightNumber;

}
