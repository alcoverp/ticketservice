package com.bharath.springcloud.dto;

import java.math.BigDecimal;

public record Price(Long id, String code, String description, BigDecimal price) {}
