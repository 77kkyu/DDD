package com.example.dddstart.common.ui;

import java.util.List;

public class ValidationErrorException extends RuntimeException {
	private List<ValidationError> errors;

	public ValidationErrorException(List<ValidationError> errors) {
		this.errors = errors;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}
}
