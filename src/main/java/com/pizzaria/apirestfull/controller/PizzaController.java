package com.pizzaria.apirestfull.controller;

import com.pizzaria.apirestfull.model.dto.PizzaDTO;
import com.pizzaria.apirestfull.service.PizzaService;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<PizzaDTO>> create(@RequestBody PizzaDTO pizzaDTO) {
        try {
            return ResponseEntity.ok(this.pizzaService.create(pizzaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<PizzaDTO>>> findAll() {
        try {
            return ResponseEntity.ok(this.pizzaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<PizzaDTO>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.pizzaService.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ApiResponse<PizzaDTO>> update(@PathVariable Long id,
                                                        @RequestBody PizzaDTO pizzaDTO) {
        try {
            return ResponseEntity.ok(this.pizzaService.update(id, pizzaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<PizzaDTO>> update(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.pizzaService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }
}
