package com.example.dddstart.order.domain;

import com.example.dddstart.member.domain.MemberId;

public interface OrdererService {
	Orderer createOrderer(MemberId ordererMemberId);
}
