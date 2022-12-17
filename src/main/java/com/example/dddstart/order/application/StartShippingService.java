package com.example.dddstart.order.application;

import com.example.dddstart.common.ui.VersionConflictException;
import com.example.dddstart.order.application.exception.NoOrderException;
import com.example.dddstart.order.domain.Order;
import com.example.dddstart.order.domain.OrderNo;
import com.example.dddstart.order.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StartShippingService {
	private OrderRepository orderRepository;

	public StartShippingService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Transactional
	public void startShipping(StartShippingRequest req) {
		Optional<Order> orderOpt = orderRepository.findById(new OrderNo(req.getOrderNumber()));
		Order order = orderOpt.orElseThrow(() -> new NoOrderException());
		if (order.matchVersion(req.getVersion())) {
			throw new VersionConflictException();
		}
		order.startShipping();
	}
}
