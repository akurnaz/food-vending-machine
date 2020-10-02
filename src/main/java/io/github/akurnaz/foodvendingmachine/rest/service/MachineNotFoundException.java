package io.github.akurnaz.foodvendingmachine.rest.service;

public class MachineNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MachineNotFoundException(String message) {
		super(message);
	}
}
