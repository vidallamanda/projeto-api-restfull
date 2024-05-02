package com.pizzaria.apirestfull.controller;

import com.pizzaria.apirestfull.model.dto.DrinkDTO;
import com.pizzaria.apirestfull.service.DrinkService;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<DrinkDTO>> create(@RequestBody DrinkDTO drinkDTO) {
        try {
            return ResponseEntity.ok(this.drinkService.create(drinkDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<DrinkDTO>>> findAll() {
        try {
            return ResponseEntity.ok(this.drinkService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<DrinkDTO>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.drinkService.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ApiResponse<DrinkDTO>> update(@PathVariable Long id,
                                                        @RequestBody DrinkDTO drinkDTO) {
        try {
            return ResponseEntity.ok(this.drinkService.update(id, drinkDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<DrinkDTO>> update(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.drinkService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }
}
