package com.example.dddstart.order.application;

import com.example.dddstart.order.application.exception.NoOrderException;
import com.example.dddstart.order.domain.Order;
import com.example.dddstart.order.domain.OrderNo;
import com.example.dddstart.order.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChangeShippingService {
	private OrderRepository orderRepository;

	public ChangeShippingService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Transactional
	public void changeShipping(ChangeShippingRequest changeShippingRequest) {
		Optional<Order> optionalOrder =
			orderRepository.findById(new OrderNo(changeShippingRequest.getNumber()));
		Order order = optionalOrder.orElseThrow(() -> new NoOrderException());
		order.changeShippingInfo(changeShippingRequest.getShippingInfo());
	}
}
