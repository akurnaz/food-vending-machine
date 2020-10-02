package io.github.akurnaz.foodvendingmachine.rest.service;

import io.github.akurnaz.foodvendingmachine.machine.Machine;

public interface MachineService {

	Machine findById(Long id);

	Machine add(Machine machine);

}
