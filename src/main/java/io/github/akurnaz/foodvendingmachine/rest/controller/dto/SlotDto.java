package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import lombok.Data;

@Data
public class SlotDto {
	private int number;
	private int quantity;
	private ProductDto product;

	public SlotDto(Slot slot) {
		this.number = slot.getNumber();
		this.quantity = slot.getQuantity();
		this.product = slot.getProduct() != null ? new ProductDto(slot.getProduct()) : null;
	}
}
