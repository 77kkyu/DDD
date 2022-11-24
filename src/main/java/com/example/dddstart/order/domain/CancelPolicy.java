package com.example.dddstart.order.domain;

public interface CancelPolicy {
	boolean hasCancellationPermission(Order order, Canceller canceller);
}
