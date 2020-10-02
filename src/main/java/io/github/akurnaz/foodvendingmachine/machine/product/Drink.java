package io.github.akurnaz.foodvendingmachine.machine.product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Drink extends Product {
	private DrinkType type;
	private Set<Choice> necessaryChoices;

	public Drink(String name, BigDecimal unitPrice, DrinkType type) {
		super(name, unitPrice);
		this.type = type;
		choices();
	}

	@Override
	public Set<Choice> choices() {
		if (necessaryChoices != null) {
			return necessaryChoices;
		}
		necessaryChoices = new HashSet<>();
		necessaryChoices.add(Choice.QANTITY);
		if (type.equals(DrinkType.HOT)) {
			necessaryChoices.add(Choice.SUGAR_AMOUNT);
		}
		return necessaryChoices;
	}

	@Override
	public BigDecimal calculateAmount(Map<Choice, Integer> choices) {
		if (!choices.keySet().containsAll(necessaryChoices)) {
			throw new InvalidChoiceException("Invalid choice");
		}
		Integer quantity = choices.get(Choice.QANTITY);
		return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
	}

}
