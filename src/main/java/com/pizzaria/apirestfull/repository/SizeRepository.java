package com.pizzaria.apirestfull.repository;

import com.pizzaria.apirestfull.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

}
