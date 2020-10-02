package io.github.akurnaz.foodvendingmachine.rest.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.akurnaz.foodvendingmachine.machine.Machine;
import io.github.akurnaz.foodvendingmachine.machine.PaymentType;
import io.github.akurnaz.foodvendingmachine.machine.product.Choice;
import io.github.akurnaz.foodvendingmachine.machine.product.Product;
import io.github.akurnaz.foodvendingmachine.machine.slot.Slot;
import io.github.akurnaz.foodvendingmachine.rest.controller.dto.PaymentInfoDto;
import io.github.akurnaz.foodvendingmachine.rest.controller.dto.PrePaymentDto;
import io.github.akurnaz.foodvendingmachine.rest.service.MachineService;
import io.github.akurnaz.foodvendingmachine.rest.service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/api/payments" })
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
	private static final String PATH = "/api/payments";
	private final PaymentService paymentService;
	private final MachineService machineService;

	@PostMapping("/pre-payment")
	public PrePaymentDto prePayment(@RequestBody OrderDto orderDto) {
		log.debug("POST " + PATH + "/pre-payment {}", orderDto);
		Machine machine = machineService.findById(orderDto.getMachineId());
		Slot slot = machine.findSlot(orderDto.getSlotNumber());
		Product product = slot.getProduct();
		BigDecimal totalAmount = product.calculateAmount(orderDto.getChoices());
		return new PrePaymentDto(totalAmount, machine.getPaymentTypes());
	}

	@PostMapping("/pay")
	public PaymentInfoDto getMachine(@RequestBody PaymentDto paymentDto) {
		log.debug("POST " + PATH + "/pay {}", paymentDto);
		Machine machine = machineService.findById(paymentDto.getMachineId());
		Slot slot = machine.findSlot(paymentDto.getSlotNumber());
		Product product = slot.getProduct();
		BigDecimal refund = paymentService.pay(paymentDto.getMachineId(), paymentDto.getSlotNumber(),
				paymentDto.getChoices(), paymentDto.getPayment().getPaymentType(), paymentDto.getPayment().getAmount());
		Integer quantity = paymentDto.getChoices().get(Choice.QANTITY);
		return new PaymentInfoDto(product, quantity, paymentDto.getPayment().getPaymentType(), refund);
	}

	@Data
	private static class OrderDto {
		private Long machineId;
		private Integer slotNumber;
		private Map<Choice, Integer> choices;
	}

	@Data
	private static class PaymentDto {
		private Long machineId;
		private Integer slotNumber;
		private Map<Choice, Integer> choices;
		private PaymentMethod payment;

		@Data
		private static class PaymentMethod {
			private PaymentType paymentType;
			private BigDecimal amount;
		}
	}

}
