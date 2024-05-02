package com.pizzaria.apirestfull.controller;

import com.pizzaria.apirestfull.model.dto.BatchDTO;
import com.pizzaria.apirestfull.service.BatchService;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<BatchDTO>> create(@RequestBody BatchDTO batchDTO) {
        try {
            return ResponseEntity.ok(this.batchService.create(batchDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<BatchDTO>>> findAll() {
        try {
            return ResponseEntity.ok(this.batchService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/find-one/{id}", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<BatchDTO>> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.batchService.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ApiResponse<BatchDTO>> update(@PathVariable Long id,
                                                        @RequestBody BatchDTO batchDTO) {
        try {
            return ResponseEntity.ok(this.batchService.update(id, batchDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<BatchDTO>> update(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.batchService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }
}
