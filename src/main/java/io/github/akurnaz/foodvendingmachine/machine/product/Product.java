package io.github.akurnaz.foodvendingmachine.machine.product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public abstract class Product {
	private String name;
	private BigDecimal unitPrice;

	public Product(String name, BigDecimal unitPrice) {
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public abstract Set<Choice> choices();

	public abstract BigDecimal calculateAmount(Map<Choice, Integer> choices);

}
