package io.github.akurnaz.foodvendingmachine.rest.service;

import java.math.BigDecimal;
import java.util.Map;

import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Choice;

public interface PaymentService {

	BigDecimal pay(Long machineId, Integer slotNumber, Map<Choice, Integer> choices, PaymentType paymentType,
			BigDecimal amount);

}
