package io.github.akurnaz.foodvendingmachine.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import io.github.akurnaz.foodvendingmachine.rest.controller.dto.MachineDto;
import io.github.akurnaz.foodvendingmachine.rest.controller.dto.SlotDetailDto;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/api/machines" })
@RequiredArgsConstructor
@Slf4j
public class MachineController {
	private static final String PATH = "/api/machines";

	private final MachineService machineService;

	@GetMapping("/{id}")
	public MachineDto getMachine(@PathVariable Long id) {
		log.debug("GET " + PATH + "/{id}", id);
		Machine machine = machineService.findById(id);
		return new MachineDto(machine);
	}

	@GetMapping("/{id}/slots/{number}")
	public SlotDetailDto getChoices(@PathVariable Long id, @PathVariable Integer number) {
		log.debug("GET " + PATH + "/{id}/slots/{number}", id, number);
		Machine machine = machineService.findById(id);
		Slot slot = machine.findSlot(number);
		return new SlotDetailDto(slot);
	}

}
