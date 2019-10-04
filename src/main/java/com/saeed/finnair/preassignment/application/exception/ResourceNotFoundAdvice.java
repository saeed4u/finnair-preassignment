package com.saeed.finnair.preassignment.application.exception;

import com.saeed.finnair.preassignment.domain.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError getBody(ResourceNotFoundException ex){
		return new ApiError(ex.getMessage());
	}

}
