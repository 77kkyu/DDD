package com.example.dddstart.order.application;

import com.example.dddstart.order.application.exception.NoCancellablePermission;
import com.example.dddstart.order.application.exception.NoOrderException;
import com.example.dddstart.order.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelOrderService {
	private OrderRepository orderRepository;
	private CancelPolicy cancelPolicy;

	public CancelOrderService(OrderRepository orderRepository, CancelPolicy cancelPolicy) {
		this.orderRepository = orderRepository;
		this.cancelPolicy = cancelPolicy;
	}

	@Transactional
	public void cancel(OrderNo orderNo, Canceller canceller) {
		Order order = orderRepository.findById(orderNo)
			.orElseThrow(() -> new NoOrderException());
		if (!cancelPolicy.hasCancellationPermission(order, canceller))
			throw new NoCancellablePermission();

		order.cancel();
	}
}
