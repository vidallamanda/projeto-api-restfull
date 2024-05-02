package com.pizzaria.apirestfull.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

public class DrinkOrdered {

    @Column(nullable = false)
    private Integer quant;

    public DrinkOrdered() {
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }
}
