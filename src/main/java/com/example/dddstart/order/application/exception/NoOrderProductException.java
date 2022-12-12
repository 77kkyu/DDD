package com.example.dddstart.order.application.exception;

public class NoOrderProductException extends RuntimeException {
	private String productId;

	public NoOrderProductException(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
}
