package io.github.akurnaz.foodvendingmachine.rest.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;
import io.github.akurnaz.foodvendingmachine.rest.service.PaymentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final MachineService machineService;

	@Override
	public BigDecimal pay(Long machineId, Integer slotNumber, Map<Choice, Integer> choices, PaymentType paymentType,
			BigDecimal amount) {
		Machine machine = machineService.findById(machineId);
		Slot slot = machine.findSlot(slotNumber);
		Product product = slot.getProduct();
		BigDecimal totalAmount = product.calculateAmount(choices);
		BigDecimal refund = amount.subtract(totalAmount);
		if (refund.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		slot.repare(choices);
		return refund;
	}

}
