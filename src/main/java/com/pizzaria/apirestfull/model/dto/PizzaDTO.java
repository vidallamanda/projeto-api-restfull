package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Pizza;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PizzaDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome da pizza.")
    private String name;

    @NotNull(message = "É necessário informar o preço base da pizza.")
    private BigDecimal basePrice;

    private boolean customized;

    public PizzaDTO() {

    }

    public PizzaDTO(Long id) {
        this.id = id;
    }

    public PizzaDTO(Pizza pizza) {
        this.id = pizza.getId();
        this.name = pizza.getName();
        this.basePrice = pizza.getBasePrice();
        this.customized = pizza.isCustomized();
    }

    public static Pizza convert(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDTO.getId());
        pizza.setName(pizzaDTO.getName());
        pizza.setBasePrice(pizzaDTO.getBasePrice());
        pizza.setCustomized(pizzaDTO.isCustomized());

        return pizza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isCustomized() {
        return customized;
    }

    public void setCustomized(boolean customized) {
        this.customized = customized;
    }
}
