package com.paulina.tg.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String categoryId;
    private int secure_inventory;
    private String description;
}
