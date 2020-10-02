package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import io.github.akurnaz.foodvendingmachine.machine.product.Product;
import lombok.Data;

@Data
public class ProductDto {
	private String name;

	public ProductDto(Product product) {
		this.name = product.getName();
	}

}
