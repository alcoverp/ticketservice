package com.bharath.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.springcloud.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
