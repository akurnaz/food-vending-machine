package io.github.akurnaz.foodvendingmachine.machine.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrinkTest {
	private Drink drink;
	private String name;
	private BigDecimal unitPrice;

	@BeforeEach
	void BeforeEach() {
		name = "Coffe";
		unitPrice = BigDecimal.valueOf(10.00);
		drink = new Drink(name, unitPrice, DrinkType.HOT);
	}

	@Test
	void choices_givenNecessaryChoices_thenEqualsChoices() {
		// given
		Set<Choice> necessaryChoices = new HashSet<>();
		necessaryChoices.add(Choice.QANTITY);
		necessaryChoices.add(Choice.SUGAR_AMOUNT);

		// then
		Assertions.assertEquals(necessaryChoices, drink.choices());
	}

	@Test
	void calculateAmount_givenQuantity_thenEqualsExpectedAmount() {
		// given
		Integer quantity = 2;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.QANTITY, quantity);
		choices.put(Choice.SUGAR_AMOUNT, 0);
		BigDecimal expectedAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));

		// then
		Assertions.assertEquals(expectedAmount, drink.calculateAmount(choices));
	}

	@Test
	void calculateAmount_givenInvalidChoice_thenThrowsInvalidChoiceException() {
		// given
		Integer quantity = 2;
		Map<Choice, Integer> choices = new HashMap<>();
		choices.put(Choice.SUGAR_AMOUNT, quantity);

		// then
		Assertions.assertThrows(InvalidChoiceException.class, () -> drink.calculateAmount(choices));
	}

	@Test
	void getName_givenName_thenEqualsName() {
		// given

		// then
		Assertions.assertEquals(name, drink.getName());
	}
}
