package com.pizzaria.apirestfull.repository;

import com.pizzaria.apirestfull.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

}
