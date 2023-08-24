package com.bharath.springcloud.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bharath.springcloud.dto.PriceDto;
import com.bharath.springcloud.model.Ticket;
import com.bharath.springcloud.repository.TicketRepository;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
	private static final String priceServiceURI = "http://localhost:9093/api/v1/price";
	private final TicketRepository ticketRepository;

	public TicketController(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@GetMapping("{ticketCode}/{numberOfTickets}")
	public Ticket getTotalPrice(@PathVariable String ticketCode, @PathVariable Integer numberOfTickets) {
		final RestTemplate restTemplate = new RestTemplate();
		final PriceDto priceDto = restTemplate.getForObject(priceServiceURI + "/" + ticketCode, PriceDto.class);

		assert priceDto != null;
		final BigDecimal totalPrice = BigDecimal.valueOf(numberOfTickets).multiply(priceDto.price());
		final Ticket ticket = new Ticket(ticketCode, numberOfTickets, totalPrice);

		return ticketRepository.save(ticket);
	}
}
