package com.example.dddstart.category.query.product;

import com.example.dddstart.category.domain.category.CategoryId;
import com.example.dddstart.category.domain.product.ProductId;
import com.example.dddstart.category.query.category.CategoryData;
import com.example.dddstart.category.query.category.CategoryDataDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ProductQueryService {

	private final ProductDataDao productDataDao;
	private final CategoryDataDao categoryDataDao;

	public ProductQueryService(ProductDataDao productDataDao, CategoryDataDao categoryDataDao) {
		this.productDataDao = productDataDao;
		this.categoryDataDao = categoryDataDao;
	}

	@Transactional
	public CategoryProduct getProductInCategory(Long categoryId, int page, int size) {
		CategoryData categoryData = categoryDataDao.findById(new CategoryId(categoryId))
			.orElseThrow(() -> new RuntimeException());

		Page<ProductData> productDataPage =
			productDataDao.findByCategoryIdsContains(categoryData.getId(), Pageable.ofSize(size).withPage(page - 1));
		return new CategoryProduct(
			categoryData,
			toSummary(productDataPage.getContent()),
			page,
			productDataPage.getSize(),
			productDataPage.getTotalElements(),
			productDataPage.getTotalPages()
		);
	}

	private List<ProductSummary> toSummary(List<ProductData> products) {
		return products.stream()
			.map(prod -> new ProductSummary(
				prod.getId().getId(),
				prod.getName(),
				prod.getPrice().getValue(),
				prod.getFirstIamgeThumbnailPath())
			).collect(toList());
	}

	public Optional<ProductData> getProduct(String productId) {
		return productDataDao.findById(new ProductId(productId));
	}

}
