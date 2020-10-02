package io.github.akurnaz.foodvendingmachine.rest.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineNotFoundException;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;

@Service
public class MachineServiceImpl implements MachineService {

	private Map<Long, Machine> machines = new HashMap<>();

	@Override
	public Machine findById(Long id) {
		Machine machine = machines.get(id);
		if (machine == null) {
			throw new MachineNotFoundException("Machine not found");
		}
		return machine;
	}

	@Override
	public Machine add(Machine machine) {
		if (machines.get(machine.getId()) == null) {
			machines.put(machine.getId(), machine);
		}
		return machine;
	}

}
