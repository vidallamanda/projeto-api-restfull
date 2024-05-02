package com.pizzaria.apirestfull.repository;

import com.pizzaria.apirestfull.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
