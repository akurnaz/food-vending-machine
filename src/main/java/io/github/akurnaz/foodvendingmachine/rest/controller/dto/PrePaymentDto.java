package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import lombok.Data;

@Data
public class PrePaymentDto {
	private BigDecimal totalAmount;
	private Set<PaymentType> paymentTypes;

	public PrePaymentDto(BigDecimal totalAmount, Set<PaymentType> paymentTypes) {
		this.totalAmount = totalAmount;
		this.paymentTypes = paymentTypes;
	}
}
