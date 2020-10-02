package io.github.akurnaz.foodvendingmachine.machine.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {
	private Food food;
	private String name;
	private BigDecimal unitPrice;

	@BeforeEach
	void BeforeEach() {
		name = "Halley";
		unitPrice = BigDecimal.valueOf(3.50);
		food = new Food(name, unitPrice);
	}

	@Test
	void choices_givenNecessaryChoices_thenEqualsChoices() {
		// given
		Set<Choice> necessaryChoices = new HashSet<>();
		necessaryChoices.add(Choice.QANTITY);
		// then
		Assertions.assertEquals(necessaryChoices, food.choices());
	}

	@Test
	void calculateAmount_givenQuantity_thenEqualsExpectedAmount() {
		// given
		Integer quantity = 2;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, quantity);
		BigDecimal expectedAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));

		// then
		Assertions.assertEquals(expectedAmount, food.calculateAmount(choices));
	}

	@Test
	void calculateAmount_givenInvalidChoice_thenThrowsInvalidChoiceException() {
		// given
		Integer quantity = 2;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.SUGAR_AMOUNT, quantity);

		// then
		Assertions.assertThrows(InvalidChoiceException.class, () -> food.calculateAmount(choices));
	}

	@Test
	void getName_givenName_thenEqualsName() {
		// given

		// then
		Assertions.assertEquals(name, food.getName());
	}
}
