package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import java.math.BigDecimal;

import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;
import lombok.Data;

@Data
public class PaymentInfoDto {
	private ProductDto product;
	private Integer quantity;
	private PaymentType paymentType;
	private BigDecimal refund;

	public PaymentInfoDto(Product product, Integer quantity, PaymentType paymentType, BigDecimal refund) {
		this.product = new ProductDto(product);
		this.quantity = quantity;
		this.paymentType = paymentType;
		this.refund = refund;
	}

}
