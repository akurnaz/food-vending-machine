package io.github.akurnaz.foodvendingmachine.rest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import lombok.Data;

@Data
public class MachineDto {
	private Long id;
	private List<SlotDto> slots;

	public MachineDto(Machine machine) {
		this.id = machine.getId();
		this.slots = machine.getSlots().stream().map(SlotDto::new).collect(Collectors.toList());
	}
}
