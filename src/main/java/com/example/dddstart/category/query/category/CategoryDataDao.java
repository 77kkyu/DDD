package com.example.dddstart.category.query.category;

import com.example.dddstart.category.domain.category.CategoryId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryDataDao extends Repository<CategoryData, CategoryId> {

	Optional<CategoryData> findById(CategoryId id);

	List<CategoryData> findAll();

}
