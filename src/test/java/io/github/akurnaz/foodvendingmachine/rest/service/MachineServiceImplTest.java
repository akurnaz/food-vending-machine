package io.github.akurnaz.foodvendingmachine.rest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineNotFoundException;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;
import io.github.akurnaz.foodvendingmachine.rest.service.impl.MachineServiceImpl;

class MachineServiceImplTest {
	private MachineService machineService;

	@BeforeEach
	void beforeEach() {
		machineService = new MachineServiceImpl();
	}

	@Test
	void add_givenMachine_thenEqualsMachine() {
		// given
		Machine machine = Machine.builder(1).build();

		// then
		Assertions.assertEquals(machine, machineService.add(machine));
	}

	@Test
	void findById_givenId_thenEqualsMachine() {
		// given
		Long id = 1L;
		Machine machine = machineService.add(Machine.builder(id).build());

		// then
		Assertions.assertEquals(machine, machineService.findById(id));
	}

	@Test
	void findById_givenId_thenThrowsMachineNotFoundException() {
		// given
		Long id = 100L;

		// then
		Assertions.assertThrows(MachineNotFoundException.class, () -> machineService.findById(id));
	}
}
