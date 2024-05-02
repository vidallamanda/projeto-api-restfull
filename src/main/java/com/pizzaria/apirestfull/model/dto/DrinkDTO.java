package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Drink;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DrinkDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome da bebida.")
    private String name;

    @NotNull(message = "É necessário informar o preço da bebida.")
    private BigDecimal price;

    public DrinkDTO() {

    }

    public DrinkDTO(Long id) {
        this.id = id;
    }

    public DrinkDTO(Drink drink) {
        this.id = drink.getId();
        this.name = drink.getName();
        this.price = drink.getPrice();
    }

    public static Drink convert(DrinkDTO drinkDTO) {
        Drink drink = new Drink();
        drink.setId(drinkDTO.getId());
        drink.setName(drinkDTO.getName());
        drink.setPrice(drinkDTO.getPrice());

        return drink;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
