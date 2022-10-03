package com.example.dddstart.integration.infra;

import com.example.dddstart.eventstore.api.EventEntry;
import com.example.dddstart.integration.EventSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SysoutEventSender implements EventSender {

	@Override
	public void send(EventEntry event) {
		log.info("EventSender send event : " + event);
	}

}
