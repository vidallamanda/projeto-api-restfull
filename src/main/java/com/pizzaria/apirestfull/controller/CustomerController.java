package com.pizzaria.apirestfull.controller;

import com.pizzaria.apirestfull.model.dto.CustomerDTO;
import com.pizzaria.apirestfull.service.CustomerService;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<CustomerDTO>> create(@RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(this.customerService.create(customerDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> findAll() {
        try {
            return ResponseEntity.ok(this.customerService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<CustomerDTO>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.customerService.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ApiResponse<CustomerDTO>> update(@PathVariable Long id,
                                                        @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(this.customerService.update(id, customerDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<CustomerDTO>> update(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.customerService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }
}
