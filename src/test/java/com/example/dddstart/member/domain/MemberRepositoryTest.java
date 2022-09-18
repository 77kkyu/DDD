package com.example.dddstart.member.domain;

import com.example.dddstart.common.model.Email;
import com.example.dddstart.helper.DbHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		dbHelper.clear();
	}

	@Test
	public void findById() {
		jdbcTemplate.update(
			"""
					insert into member values (?, ?, ?, ?, ?)
					""",
			"member1", "name", "p1", false, "email1@email.com,email2@email.com"
		);
		Optional<Member> memberOptional = memberRepository.findById(MemberId.of("member1"));
		assertThat(memberOptional).isPresent();
		Member member = memberOptional.get();
		assertThat(member.getEmails().getEmails()).contains(
			Email.of("email1@email.com"), Email.of("email2@email.com")
		);
	}

	@Test
	public void save() {
		Member member = new Member(MemberId.of("id1"), "이름2");
		Set<Email> emails = new HashSet<>();
		emails.add(Email.of("mail1@mail.com"));
		emails.add(Email.of("mail2@mail.com"));
		member.changeEmails(emails);
		memberRepository.save(member);

		SqlRowSet rs =
			jdbcTemplate.queryForRowSet("select * from member where member_id = ?", "id1");
		assertThat(rs.next()).isTrue();
		assertThat(rs.getString("emails")).contains("mail1@mail.com", "mail2@mail.com");
	}

	@Transactional
	@Test
	public void findByIdForUpdate() {
		jdbcTemplate.update(
			"""
					insert into member values (?, ?, ?, ?, ?)
					""",
			"member1", "이름", "p1", false, "email1@email.com,email2@email.com"
		);
		Optional<Member> member1 = memberRepository.findByIdForUpdate(MemberId.of("member1"));
	}

}
