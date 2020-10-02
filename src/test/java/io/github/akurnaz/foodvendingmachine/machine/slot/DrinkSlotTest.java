package io.github.akurnaz.foodvendingmachine.machine.slot;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Drink;
import io.github.akurnaz.foodvendingmachine.machine.product.DrinkType;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;

class DrinkSlotTest {
	private int number;
	private int capacity;
	private Product product;
	private int quantity;
	private DrinkSlot drinkSlot;

	@BeforeEach
	void BeforeEach() {
		number = 1;
		capacity = 10;
		product = new Drink("Coca Cola", BigDecimal.valueOf(10), DrinkType.COLD);
		quantity = 5;

		drinkSlot = new DrinkSlot(number, capacity);
		drinkSlot.setProduct(product);
		drinkSlot.addProduct(quantity);
	}

	@Test
	void setProduct_givenFood_thenEqualsFood() {
		// given

		// then
		Assertions.assertEquals(product, drinkSlot.getProduct());
	}

	@Test
	void addProduct_givenEnoughQuatity_thenEqualsQuantity() {
		// given

		// then
		Assertions.assertEquals(quantity, drinkSlot.getQuantity());
	}

	@Test
	void addProduct_givenOverCapacity_thenThrowsException() {
		// given
		int quatity = capacity + 5;

		// then
		Assertions.assertThrows(OverCapacityException.class, () -> drinkSlot.addProduct(quatity));
	}

	@Test
	void repare_givenEnoughQuatity_thenEqualsQuantity() {
		// given
		int selectedQuantity = 1;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, selectedQuantity);

		// then
		drinkSlot.repare(choices);
		Assertions.assertEquals(quantity - selectedQuantity, drinkSlot.getQuantity());
	}

	@Test
	void repare_givenOverCapacity_thenThrowsException() {
		// given
		int selectedQuantity = 6;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, selectedQuantity);

		// then
		Assertions.assertThrows(InsufficientQuantityException.class, () -> drinkSlot.repare(choices));
	}

	@Test
	void getNumber_givenSlotNumber_thenEqualsSlotNumber() {
		// given

		// then
		Assertions.assertEquals(number, drinkSlot.getNumber());
	}

}
