package io.github.akurnaz.foodvendingmachine.machine.slot;

public class OverCapacityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OverCapacityException(String message) {
		super(message);
	}
}
