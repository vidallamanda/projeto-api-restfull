package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Batch;
import jakarta.validation.constraints.*;

public class BatchDTO {
    private Long id;

    @NotNull(message = "É necessário informar a quantidade de fornadas.")
    @Min(value = 1, message = "A quantidade mínima de fornadas é uma (1).")
    private Integer quantBatch;

    @NotNull(message = "É necessário informar a quantidade de pizzas.")
    @Min(value = 1, message = "A quantidade mínima de pizzas é uma (1).")
    private Integer quantPizza;

    public BatchDTO() {

    }

    public BatchDTO(Long id) {
        this.id = id;
    }

    public BatchDTO(Batch batch) {
        this.id = batch.getId();
        this.quantBatch = batch.getQuantBatch();
        this.quantPizza = batch.getQuantPizza();
    }

    public static Batch convert(BatchDTO batchDTO) {
         Batch batch = new Batch();
         batch.setId(batchDTO.getId());
         batch.setQuantBatch(batchDTO.getQuantBatch());
         batch.setQuantPizza(batchDTO.getQuantPizza());

         return batch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantBatch() {
        return quantBatch;
    }

    public void setQuantBatch(Integer quantBatch) {
        this.quantBatch = quantBatch;
    }

    public Integer getQuantPizza() {
        return quantPizza;
    }

    public void setQuantPizza(Integer quantPizza) {
        this.quantPizza = quantPizza;
    }
}
