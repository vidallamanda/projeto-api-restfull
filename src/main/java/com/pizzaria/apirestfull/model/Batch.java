package com.pizzaria.apirestfull.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantBatch;

    @Column(nullable = false)
    private Integer quantPizza;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    public Batch() {
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
