package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Batch;
import com.pizzaria.apirestfull.model.dto.BatchDTO;
import com.pizzaria.apirestfull.repository.BatchRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService {
    @Autowired
    private BatchRepository batchRepository;

    public ApiResponse<BatchDTO> create(BatchDTO batchDTO) {
        try {
            Batch createdBatch = BatchDTO.convert(batchDTO);
            createdBatch = this.batchRepository.save(createdBatch);
            return new ApiResponse<>(201, "Fornada cadastrada com sucesso!",
                    new BatchDTO(createdBatch));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<BatchDTO>> findAll() {
        try {
            List<Batch> foundAllBatch = this.batchRepository.findAll();
            return new ApiResponse<>(200, "Listagem de fornadas realizada com sucesso!",
                    foundAllBatch.stream().map(BatchDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<BatchDTO> findOne(Long id) {
        try {
            Optional<Batch> foundOneBatch = this.batchRepository.findById(id);

            return foundOneBatch.map(batch -> new ApiResponse<>(200, "Detalhamento de fornadas realizado com " +
                    "sucesso!", new BatchDTO(batch))).orElseGet(() -> new ApiResponse<>(204, "Fornada não encontrada!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<BatchDTO> update(Long id, BatchDTO batchDTO) {
        try {
            ApiResponse<BatchDTO> existBatch = this.findOne(id);

            if (existBatch.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Essa fornada não " +
                        "existe!", null);
            }

            Batch updatedBatch = BatchDTO.convert(batchDTO);
            updatedBatch.setId(id);
            updatedBatch = this.batchRepository.save(updatedBatch);

            return new ApiResponse<>(200, "Fornada editada com sucesso!",
                    new BatchDTO(updatedBatch));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<BatchDTO> delete(Long id) {
        try {
            ApiResponse<BatchDTO> existBatch = this.findOne(id);
            if (existBatch.getStatus() != 200) {
                return new ApiResponse<>(400, "Fornada não encontrada! Não foi possível " +
                        "excluir registro", null);
            }

            this.batchRepository.deleteById(id);
            return new ApiResponse<>(200, "Fornada excluída com sucesso!",
                    existBatch.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
