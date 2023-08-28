package com.bharath.springcloud.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.springcloud.dto.Price;
import com.bharath.springcloud.model.Ticket;
import com.bharath.springcloud.repository.TicketRepository;
import com.bharath.springcloud.restclient.PriceService;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
	private final PriceService priceService;
	private final TicketRepository ticketRepository;

	public TicketController(PriceService priceService, TicketRepository ticketRepository) {
		this.priceService = priceService;
		this.ticketRepository = ticketRepository;
	}

	@GetMapping("{ticketCode}/{numberOfTickets}")
	public Ticket getTotalPrice(@PathVariable String ticketCode, @PathVariable Integer numberOfTickets) {
		final Price price = priceService.getPrice(ticketCode);
		final BigDecimal totalPrice = BigDecimal.valueOf(numberOfTickets).multiply(price.price());
		final Ticket ticket = new Ticket(ticketCode, numberOfTickets, totalPrice);

		return ticketRepository.save(ticket);
	}
}
