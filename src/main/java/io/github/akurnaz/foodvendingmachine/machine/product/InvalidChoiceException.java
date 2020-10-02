package io.github.akurnaz.foodvendingmachine.machine.product;

public class InvalidChoiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidChoiceException(String message) {
		super(message);
	}
}
