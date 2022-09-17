package com.example.dddstart.eventstore.api;

public class PayloadConvertException extends RuntimeException {

	public PayloadConvertException(Exception e) {
		super(e);
	}

}
