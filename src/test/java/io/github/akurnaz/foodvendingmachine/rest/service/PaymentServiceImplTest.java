package io.github.akurnaz.foodvendingmachine.rest.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Food;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;
import io.github.akurnaz.foodvendingmachine.rest.service.PaymentService;
import io.github.akurnaz.foodvendingmachine.rest.service.impl.InsufficientBalanceException;
import io.github.akurnaz.foodvendingmachine.rest.service.impl.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
	private PaymentService paymentService;
	private Long machineId;
	private Integer slotNumber;
	private Map<Choice, Integer> choices;
	private PaymentType paymentType;

	@Mock
	private MachineService machineService;

	@BeforeEach
	void beforeEach() {
		paymentService = new PaymentServiceImpl(machineService);
		machineId = 1L;
		slotNumber = 1;
		choices = new HashMap<>();
		choices.put(Choice.QANTITY, 2);
		paymentType = PaymentType.CASH_BANKNOT;
		
		Machine machine = Machine.builder(machineId)
				.numbersOfFoodSlot(20)
				.numbersOfDrinkSlot(10)
				.slotCapacity(30)
				.paymentTypes(Stream.of(PaymentType.values()).collect(Collectors.toCollection(HashSet::new)))
				.build();
		
		Slot slot = machine.findSlot(slotNumber);
		slot.setProduct(new Food("Halley", BigDecimal.valueOf(3.50)));
		slot.addProduct(5);
		Mockito.when(machineService.findById(machineId)).thenReturn(machine);
	}

	@Test
	void pay_givenSufficientBalance_thenEqualsRefund() {
		// given
		BigDecimal amount = BigDecimal.valueOf(100);

		// then
		Assertions.assertEquals(BigDecimal.valueOf(93.00),
				paymentService.pay(machineId, slotNumber, choices, paymentType, amount));
	}

	@Test
	void pay_givenInsufficientBalance_thenThrowsInsufficientBalanceException() {
		// given
		BigDecimal amount = BigDecimal.valueOf(5);

		// then
		Assertions.assertThrows(InsufficientBalanceException.class,
				() -> paymentService.pay(machineId, slotNumber, choices, paymentType, amount));
	}
}
 