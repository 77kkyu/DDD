package com.example.dddstart.member.domain;

import com.example.dddstart.common.event.Event;

public class MemberBlockedEvent extends Event {

    private String memberId;

    public MemberBlockedEvent(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
