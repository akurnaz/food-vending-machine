package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SlotDetailDto extends SlotDto {
	private ProductDetailDto product;

	public SlotDetailDto(Slot slot) {
		super(slot);
		this.product = slot.getProduct() != null ? new ProductDetailDto(slot.getProduct()) : null;
	}

}
