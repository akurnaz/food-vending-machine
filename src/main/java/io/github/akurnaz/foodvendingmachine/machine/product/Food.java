package io.github.akurnaz.foodvendingmachine.machine.product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Food extends Product {
	private Set<Choice> necessaryChoices;

	public Food(String name, BigDecimal unitPrice) {
		super(name, unitPrice);
		choices();
	}

	@Override
	public Set<Choice> choices() {
		if (necessaryChoices != null) {
			return necessaryChoices;
		}
		necessaryChoices = new HashSet<>();
		necessaryChoices.add(Choice.QANTITY);
		return necessaryChoices;
	}

	@Override
	public BigDecimal calculateAmount(Map<Choice, Integer> choices) {
		if (!necessaryChoices.containsAll(choices.keySet())) {
			throw new InvalidChoiceException("Invalid choice");
		}
		Integer quantity = choices.get(Choice.QANTITY);
		return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
	}

}
