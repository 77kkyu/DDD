package com.example.dddstart.common.jpa;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface RangeableRepository<T, ID extends Serializable>
	extends Repository<T, ID>, RangeableExecutor<T> {
}
