package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDetailDto extends ProductDto {
	private BigDecimal unitPrice;
	private Set<Choice> choices;

	public ProductDetailDto(Product product) {
		super(product);
		this.unitPrice = product.getUnitPrice();
		this.choices = product.choices();
	}

}
