package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Batch;
import com.pizzaria.apirestfull.model.Customer;
import com.pizzaria.apirestfull.model.Order;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;

    private LocalDateTime dateOrder;

    @NotNull(message = "É necessário informar o id do cliente que realizou o pedido.")
    private Long idCustomer;

    @NotNull(message = "É necessário informar o id da fornada pertencente a este pedido.")
    private Long idBatch;

    public OrderDTO() {

    }

    public OrderDTO(Long id) {
        this.id = id;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.dateOrder = LocalDateTime.now();
        this.idCustomer = order.getCustomer().getId();
        this.idBatch = order.getBatch().getId();
    }

    public static Order convert(OrderDTO orderDTO, Customer customer, Batch batch) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setDateOrder(orderDTO.getDateOrder());
        if (customer != null) {
            order.setCustomer(customer);
        }
        if (batch != null) {
            order.setBatch(batch);
        }

        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdBatch() {
        return idBatch;
    }

    public void setIdBatch(Long idBatch) {
        this.idBatch = idBatch;
    }
}
