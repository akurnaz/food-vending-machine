package io.github.akurnaz.foodvendingmachine.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.foodvendingmachine.machine.slot.DrinkSlot;
import io.github.akurnaz.foodvendingmachine.machine.slot.FoodSlot;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;


class MachineTest {
	private long id;
	private int numbersOfFoodSlot;
	private int numbersOfDrinkSlot;
	private int slotCapacity;
	private Set<PaymentType> paymentTypes;
	private Machine machine;

	@BeforeEach
	void beforeEach() {
		id = 1;
		numbersOfFoodSlot = 20;
		numbersOfDrinkSlot = 10;
		slotCapacity = 30;
		paymentTypes = Stream.of(PaymentType.values()).collect(Collectors.toCollection(HashSet::new));

		machine = Machine.builder(id)
				.numbersOfFoodSlot(numbersOfFoodSlot)
				.numbersOfDrinkSlot(numbersOfDrinkSlot)
				.slotCapacity(slotCapacity)
				.paymentTypes(paymentTypes)
				.build();
	}

	@Test
	void getSlots_given_thenEqualsSlots() {
		// given
		List<Slot> slots = new ArrayList<>(numbersOfFoodSlot + numbersOfDrinkSlot);
		for (int i = 0; i < numbersOfFoodSlot; i++) {
			slots.add(new FoodSlot(i + 1, slotCapacity));
		}

		for (int i = 0; i < numbersOfDrinkSlot; i++) {
			slots.add(new DrinkSlot(numbersOfFoodSlot + i + 1, slotCapacity));
		}
		// then
		Assertions.assertEquals(slots.size(), machine.getSlots().size());
	}

	@Test
	void findSlot_givenSlotNumber_thenEqualsSlot() {
		// given
		int slotNumber = 1;
		// then
		Assertions.assertEquals(slotNumber, machine.findSlot(slotNumber).getNumber());
	}
	
	@Test
	void findSlot_givenInvalidSlotNumber_thenThrowsSlotNotFoundException() {
		// given
		int slotNumber = 100;
		// then
		Assertions.assertThrows(SlotNotFoundException.class, () -> machine.findSlot(slotNumber));
	}

	@Test
	void getId_given_thenEqualsId() {
		// given

		// then
		Assertions.assertEquals(id, machine.getId());
	}
	
	@Test
	void getPaymentTypes_given_thenEqualsPaymentsTypes() {
		// given

		// then
		Assertions.assertEquals(paymentTypes, machine.getPaymentTypes());
	}
}
