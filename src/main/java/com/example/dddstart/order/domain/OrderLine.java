package com.example.dddstart.order.domain;

import com.example.dddstart.category.domain.product.ProductId;
import com.example.dddstart.common.jpa.MoneyConverter;
import com.example.dddstart.common.model.Money;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderLine {
	@Embedded
	private ProductId productId;

	@Convert(converter = MoneyConverter.class)
	@Column(name = "price")
	private Money price;

	@Column(name = "quantity")
	private int quantity;

	@Convert(converter = MoneyConverter.class)
	@Column(name = "amounts")
	private Money amounts;

	protected OrderLine() {

	}

	public OrderLine(
		ProductId productId,
		Money price,
		int quantity,
		Money amounts
	) {
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.amounts = amounts;
	}

	private Money calculateAmounts() {
		return price.multiply(quantity);
	}

	public ProductId getProductId() {
		return productId;
	}

	public Money getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Money getAmounts() {
		return amounts;
	}
}
