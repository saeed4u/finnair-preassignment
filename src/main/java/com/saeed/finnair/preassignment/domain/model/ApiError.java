package com.saeed.finnair.preassignment.domain.model;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ApiError {

	private String errorMessage;

	private transient HttpStatus httpStatus;

}
