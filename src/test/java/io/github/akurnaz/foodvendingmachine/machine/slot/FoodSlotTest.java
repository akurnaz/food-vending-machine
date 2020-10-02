package io.github.akurnaz.foodvendingmachine.machine.slot;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Food;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;

class FoodSlotTest {
	private int number;
	private int capacity;
	private Product product;
	private int quantity;
	private FoodSlot foodSlot;

	@BeforeEach
	void BeforeEach() {
		number = 1;
		capacity = 10;
		product = new Food("Halley", BigDecimal.valueOf(3.50));
		quantity = 5;

		foodSlot = new FoodSlot(number, capacity);
		foodSlot.setProduct(product);
		foodSlot.addProduct(quantity);
	}

	@Test
	void setProduct_givenFood_thenEqualsFood() {
		// given

		// then
		Assertions.assertEquals(product, foodSlot.getProduct());
	}

	@Test
	void addProduct_givenEnoughQuatity_thenEqualsQuantity() {
		// given

		// then
		Assertions.assertEquals(quantity, foodSlot.getQuantity());
	}

	@Test
	void addProduct_givenOverCapacity_thenThrowsException() {
		// given
		int quatity = capacity + 5;

		// then
		Assertions.assertThrows(OverCapacityException.class, () -> foodSlot.addProduct(quatity));
	}

	@Test
	void repare_givenEnoughQuatity_thenEqualsQuantity() {
		// given
		int selectedQuantity = 1;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, selectedQuantity);

		// then
		foodSlot.repare(choices);
		Assertions.assertEquals(quantity - selectedQuantity, foodSlot.getQuantity());
	}

	@Test
	void repare_givenOverCapacity_thenThrowsException() {
		// given
		int selectedQuantity = 6;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, selectedQuantity);

		// then
		Assertions.assertThrows(InsufficientQuantityException.class, () -> foodSlot.repare(choices));
	}

	@Test
	void getNumber_givenSlotNumber_thenEqualsSlotNumber() {
		// given

		// then
		Assertions.assertEquals(number, foodSlot.getNumber());
	}

}
