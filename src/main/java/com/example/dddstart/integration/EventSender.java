package com.example.dddstart.integration;

import com.example.dddstart.eventstore.api.EventEntry;

public interface EventSender {

	void send(EventEntry entry);

}
