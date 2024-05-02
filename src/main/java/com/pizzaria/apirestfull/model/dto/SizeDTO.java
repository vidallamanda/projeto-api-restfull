package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SizeDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome do tamanho.")
    private String name;

    @NotNull(message = "É necessário informar o valor do desconto.")
    private BigDecimal discount;

    public SizeDTO() {

    }

    public SizeDTO(Long id) {
        this.id = id;
    }

    public SizeDTO(Size size) {
        this.id = size.getId();
        this.name = size.getName();
        this.discount = size.getDiscount();
    }

    public static Size convert(SizeDTO sizeDTO) {
        Size size = new Size();
        size.setId(sizeDTO.getId());
        size.setName(sizeDTO.getName());
        size.setDiscount(sizeDTO.getDiscount());

        return size;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
