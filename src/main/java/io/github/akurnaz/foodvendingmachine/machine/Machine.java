package io.github.akurnaz.foodvendingmachine.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.akurnaz.foodvendingmachine.machine.slot.DrinkSlot;
import io.github.akurnaz.foodvendingmachine.machine.slot.FoodSlot;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;

public class Machine {
	private long id;
	private Set<PaymentType> paymentTypes;
	private List<Slot> slots;

	public Machine(MachineBuilder builder) {
		this.id = builder.id;
		this.paymentTypes = builder.paymentTypes;
		this.slots = new ArrayList<>(builder.numbersOfFoodSlot + builder.numbersOfDrinkSlot);

		for (int i = 0; i < builder.numbersOfFoodSlot; i++) {
			slots.add(new FoodSlot(i + 1, builder.slotCapacity));
		}

		for (int i = 0; i < builder.numbersOfDrinkSlot; i++) {
			slots.add(new DrinkSlot(builder.numbersOfFoodSlot + i + 1, builder.slotCapacity));
		}
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public Slot findSlot(int slotNumber) {
		return slots.stream().filter(s -> s.getNumber() == slotNumber).findAny()
				.orElseThrow(() -> new SlotNotFoundException("Slot not found"));
	}

	public Long getId() {
		return id;
	}

	public Set<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public static MachineBuilder builder(long id) {
		return new MachineBuilder(id);
	}

	public static class MachineBuilder {
		private long id;
		private int numbersOfFoodSlot = 0;
		private int numbersOfDrinkSlot = 0;
		private int slotCapacity = 0;
		private Set<PaymentType> paymentTypes;

		public MachineBuilder(long id) {
			this.id = id;
		}

		public MachineBuilder numbersOfFoodSlot(int numbersOfFoodSlot) {
			this.numbersOfFoodSlot = numbersOfFoodSlot;
			return this;
		}

		public MachineBuilder numbersOfDrinkSlot(int numbersOfDrinkSlot) {
			this.numbersOfDrinkSlot = numbersOfDrinkSlot;
			return this;
		}

		public MachineBuilder slotCapacity(int slotCapacity) {
			this.slotCapacity = slotCapacity;
			return this;
		}

		public MachineBuilder paymentTypes(Set<PaymentType> paymentTypes) {
			this.paymentTypes = paymentTypes;
			return this;
		}

		public Machine build() {
			return new Machine(this);
		}

	}

}
