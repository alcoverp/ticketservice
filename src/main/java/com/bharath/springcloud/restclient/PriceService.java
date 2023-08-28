package com.bharath.springcloud.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bharath.springcloud.dto.Price;

@FeignClient("PRICE-SERVICE")
public interface PriceService {
	@GetMapping("/api/v1/price/{code}")
	Price getPrice(@PathVariable String code);
}
