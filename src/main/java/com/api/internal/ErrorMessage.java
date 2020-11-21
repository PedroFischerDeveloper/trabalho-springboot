package com.api.internal;

import java.io.Serializable;

import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class ErrorMessage implements Serializable{
	
	@JsonProperty(value = "error")
	@Getter @Setter private String message;
	

	public ErrorMessage(Exception ex) {
		message = ex.getMessage();
		if(ex.getCause() instanceof ConstraintViolationException)
			message += " | "+ ((ConstraintViolationException)ex.getCause()).getSQLException().getMessage();
	}
	
	public ErrorMessage(String msg) {
		message = msg;
	}
}
