package io.github.akurnaz.foodvendingmachine;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Drink;
import io.github.akurnaz.foodvendingmachine.machine.product.DrinkType;
import io.github.akurnaz.foodvendingmachine.machine.product.Food;
import io.github.akurnaz.foodvendingmachine.machine.slot.DrinkSlot;
import io.github.akurnaz.foodvendingmachine.machine.slot.FoodSlot;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;

@SpringBootApplication
public class FoodVendingMachineApplication implements CommandLineRunner {
	
	@Autowired
	private MachineService machineService;

	public static void main(String[] args) {
		SpringApplication.run(FoodVendingMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Machine machine = Machine.builder(1L)
				.numbersOfFoodSlot(20)
				.numbersOfDrinkSlot(10)
				.slotCapacity(30)
				.paymentTypes(Stream.of(PaymentType.values()).collect(Collectors.toCollection(HashSet::new)))
				.build();

		machine = machineService.add(machine);

		FoodSlot slot1 = (FoodSlot) machine.findSlot(1);
		slot1.setProduct(new Food("Halley", BigDecimal.valueOf(3.50)));
		slot1.addProduct(10);

		DrinkSlot slot2 = (DrinkSlot) machine.findSlot(21);
		slot2.setProduct(new Drink("Coca Cola", BigDecimal.valueOf(7.00), DrinkType.COLD));
		slot2.addProduct(10);

		DrinkSlot slot3 = (DrinkSlot) machine.findSlot(22);
		slot3.setProduct(new Drink("Kahve", BigDecimal.valueOf(10.00), DrinkType.HOT));
		slot3.addProduct(10);

	}

}
