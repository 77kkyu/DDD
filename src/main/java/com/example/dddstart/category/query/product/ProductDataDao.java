package com.example.dddstart.category.query.product;

import com.example.dddstart.category.domain.category.CategoryId;
import com.example.dddstart.category.domain.product.ProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductDataDao extends Repository<ProductData, ProductId> {

	Optional<ProductData> findById(ProductId id);

	Page<ProductData> findByCategoryIdsContains(CategoryId id, Pageable pageable);

}
