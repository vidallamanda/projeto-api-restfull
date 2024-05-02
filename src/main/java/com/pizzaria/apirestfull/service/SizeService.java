package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Size;
import com.pizzaria.apirestfull.model.dto.SizeDTO;
import com.pizzaria.apirestfull.repository.SizeRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public ApiResponse<SizeDTO> create(SizeDTO sizeDTO) {
        try {
            Size createdSize = SizeDTO.convert(sizeDTO);
            createdSize = this.sizeRepository.save(createdSize);
            return new ApiResponse<>(201, "Tamanho cadastrado com sucesso!",
                    new SizeDTO(createdSize));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<SizeDTO>> findAll() {
        try {
            List<Size> foundAllSize = this.sizeRepository.findAll();
            return new ApiResponse<>(200, "Listagem de tamanho realizada com sucesso!",
                    foundAllSize.stream().map(SizeDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SizeDTO> findOne(Long id) {
        try {
            Optional<Size> foundOneSize = this.sizeRepository.findById(id);

            return foundOneSize.map(size -> new ApiResponse<>(200, "Detalhamento de tamanho realizado com sucesso!",
                    new SizeDTO(size))).orElseGet(() -> new ApiResponse<>(204, "Tamanho não encontrado!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SizeDTO> update(Long id, SizeDTO sizeDTO) {
        try {
            ApiResponse<SizeDTO> existSize = this.findOne(id);

            if (existSize.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Esse tamanho não existe!",
                        null);
            }

            Size updatedSize = SizeDTO.convert(sizeDTO);
            updatedSize.setId(id);
            updatedSize = this.sizeRepository.save(updatedSize);

            return new ApiResponse<>(200, "Tamanho editado com sucesso!",
                    new SizeDTO(updatedSize));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<SizeDTO> delete(Long id) {
        try {
            ApiResponse<SizeDTO> existSize = this.findOne(id);
            if (existSize.getStatus() != 200) {
                return new ApiResponse<>(400, "Tamanho não encontrado! Não foi possível" +
                        " excluir registro", null);
            }

            this.sizeRepository.deleteById(id);
            return new ApiResponse<>(200, "Tamanho excluído com sucesso!",
                    existSize.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
