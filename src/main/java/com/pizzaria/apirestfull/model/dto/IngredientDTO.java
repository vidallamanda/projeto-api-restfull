package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Ingredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class IngredientDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome do ingrediente.")
    private String name;

    @NotNull(message = "É necessário informar o preço do ingrediente.")
    private BigDecimal price;

    public IngredientDTO() {

    }

    public IngredientDTO(Long id) {
        this.id = id;
    }

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
    }

    public static Ingredient convert(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setPrice(ingredientDTO.getPrice());

        return ingredient;
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
