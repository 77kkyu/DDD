package com.example.dddstart.order.domain;

import com.example.dddstart.common.event.Event;

public class OrderCanceledEvent extends Event {
	private String orderNumber;

	public OrderCanceledEvent(String number) {
		super();
		this.orderNumber = number;
	}

	public String getOrderNumber() {
		return orderNumber;
	}
}
