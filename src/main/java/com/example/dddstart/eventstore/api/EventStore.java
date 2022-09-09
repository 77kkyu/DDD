package com.example.dddstart.eventstore.api;

import java.util.List;

public interface EventStore {

    void save(Object event);

    List<EventEntry> get(long offset, long limit);

}
