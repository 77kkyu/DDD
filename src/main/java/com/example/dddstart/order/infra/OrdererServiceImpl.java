package com.example.dddstart.order.infra;

import com.example.dddstart.member.domain.MemberId;
import com.example.dddstart.member.query.MemberData;
import com.example.dddstart.member.query.MemberQueryService;
import com.example.dddstart.order.domain.Orderer;
import com.example.dddstart.order.domain.OrdererService;
import org.springframework.stereotype.Service;

@Service
public class OrdererServiceImpl implements OrdererService {
	private MemberQueryService memberQueryService;

	public OrdererServiceImpl(MemberQueryService memberQueryService) {
		this.memberQueryService = memberQueryService;
	}

	@Override
	public Orderer createOrderer(MemberId ordererMemberId) {
		MemberData memberData =
			memberQueryService.getMemberData(ordererMemberId.getId());
		return new Orderer(MemberId.of(memberData.getId()), memberData.getName());
	}
}
