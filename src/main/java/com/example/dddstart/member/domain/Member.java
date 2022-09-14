package com.example.dddstart.member.domain;

import com.example.dddstart.common.event.Events;
import com.example.dddstart.common.jpa.EmailSetConverter;
import com.example.dddstart.common.model.Email;
import com.example.dddstart.common.model.EmailSet;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @EmbeddedId
    private MemberId id;

    private String name;

    @Embedded
    private Password password;

    private boolean blocked;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emails;

    protected Member() {}

    public Member(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
        Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changeEmails(Set<Email> emails) {
        this.emails = new EmailSet(emails);
    }

    public void block() {
        this.blocked = true;
        Events.raise(new MemberBlockedEvent(id.getId()));
    }

    public void unblock() {
        this.blocked = false;
        Events.raise(new MemberUnblockedEvent(id.getId()));
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.match(oldPassword)) {
            throw new IdPasswordNotMatchingException();
        }
        this.password = new Password(newPassword);
        Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
    }

    public boolean isBlocked() {
        return blocked;
    }

    public EmailSet getEmails() {
        return emails;
    }

}
