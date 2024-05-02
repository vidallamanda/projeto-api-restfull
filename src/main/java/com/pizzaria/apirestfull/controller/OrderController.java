package com.pizzaria.apirestfull.controller;

import com.pizzaria.apirestfull.model.dto.OrderDTO;
import com.pizzaria.apirestfull.service.OrderService;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<OrderDTO>> create(@RequestBody OrderDTO orderDTO) {
        try {
            return ResponseEntity.ok(this.orderService.create(orderDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<OrderDTO>>> findAll() {
        try {
            return ResponseEntity.ok(this.orderService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<OrderDTO>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.orderService.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ApiResponse<OrderDTO>> update(@PathVariable Long id,
                                                        @RequestBody OrderDTO orderDTO) {
        try {
            return ResponseEntity.ok(this.orderService.update(id, orderDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<OrderDTO>> update(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.orderService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }
}
