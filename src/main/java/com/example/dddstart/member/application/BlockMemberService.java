package com.example.dddstart.member.application;

import com.example.dddstart.member.domain.Member;
import com.example.dddstart.member.domain.MemberId;
import com.example.dddstart.member.domain.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockMemberService {

	private MemberRepository memberRepository;

	public BlockMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Transactional
	public void block(String memberId) {
		Member member = memberRepository.findById(new MemberId(memberId))
			.orElseThrow(() -> new NoMemberException());
		member.block();
	}

}
