package com.example.dddstart.order.domain;

public class ShippingStartedEvent {
	private String orderNumber;

	public ShippingStartedEvent(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}
}
