package io.github.akurnaz.foodvendingmachine.machine.slot;

import java.util.Map;

import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;

public interface Slot {

	void setProduct(Product product);

	void addProduct(int quantity);

	void repare(Map<Choice, Integer> choices);

	int getNumber();

	int getQuantity();

	Product getProduct();

}
