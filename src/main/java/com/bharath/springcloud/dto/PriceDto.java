package com.bharath.springcloud.dto;

import java.math.BigDecimal;

public record PriceDto(Long id, String code, String description, BigDecimal price) {}
