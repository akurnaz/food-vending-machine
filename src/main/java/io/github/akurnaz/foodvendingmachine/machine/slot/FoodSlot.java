package io.github.akurnaz.foodvendingmachine.machine.slot;

import java.util.Map;

import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Food;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;

public class FoodSlot implements Slot {
	private int number;
	private int capacity;
	private int quantity;
	private Food food;

	public FoodSlot(int number, int capacity) {
		this.number = number;
		this.capacity = capacity;
		this.quantity = 0;
	}

	@Override
	public void setProduct(Product product) {
		this.food = (Food) product;
	}

	@Override
	public void addProduct(int quantity) {
		if (capacity < this.quantity + quantity) {
			throw new OverCapacityException("Capacity overrun occurred");
		}
		this.quantity += quantity;
	}

	@Override
	public void repare(Map<Choice, Integer> choices) {
		if (quantity - choices.get(Choice.QANTITY) < 0) {
			throw new InsufficientQuantityException("Insufficient quantity");
		}
		this.quantity -= choices.get(Choice.QANTITY);
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public Product getProduct() {
		return food;
	}

}
