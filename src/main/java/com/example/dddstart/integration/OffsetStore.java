package com.example.dddstart.integration;

public interface OffsetStore {

	long get();
	void update(long nextOffset);

}
