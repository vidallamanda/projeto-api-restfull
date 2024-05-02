package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Ingredient;
import com.pizzaria.apirestfull.model.dto.IngredientDTO;
import com.pizzaria.apirestfull.repository.IngredientRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public ApiResponse<IngredientDTO> create(IngredientDTO ingredientDTO) {
        try {
            Ingredient createdIngredient = IngredientDTO.convert(ingredientDTO);
            createdIngredient = this.ingredientRepository.save(createdIngredient);
            return new ApiResponse<>(201, "Ingrediente cadastrado com sucesso!",
                    new IngredientDTO(createdIngredient));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<IngredientDTO>> findAll() {
        try {
            List<Ingredient> foundAllIngredient = this.ingredientRepository.findAll();
            return new ApiResponse<>(200, "Listagem de ingredientes realizada com sucesso!",
                    foundAllIngredient.stream().map(IngredientDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<IngredientDTO> findOne(Long id) {
        try {
            Optional<Ingredient> foundOneIngredient = this.ingredientRepository.findById(id);

            return foundOneIngredient.map(ingredient -> new ApiResponse<>(200, "Detalhamento de ingrediente realizado com sucesso!",
                    new IngredientDTO(ingredient))).orElseGet(() -> new ApiResponse<>(204, "Ingrediente não encontrado!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<IngredientDTO> update(Long id, IngredientDTO ingredientDTO) {
        try {
            ApiResponse<IngredientDTO> existIngredient = this.findOne(id);

            if (existIngredient.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Esse ingrediente não " +
                        "existe!", null);
            }

            Ingredient updatedIngredient = IngredientDTO.convert(ingredientDTO);
            updatedIngredient.setId(id);
            updatedIngredient = this.ingredientRepository.save(updatedIngredient);

            return new ApiResponse<>(200, "Ingrediente editado com sucesso!",
                    new IngredientDTO(updatedIngredient));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<IngredientDTO> delete(Long id) {
        try {
            ApiResponse<IngredientDTO> existIngredient = this.findOne(id);
            if (existIngredient.getStatus() != 200) {
                return new ApiResponse<>(400, "Ingrediente não encontrado! Não foi possível excluir registro", null);
            }

            this.ingredientRepository.deleteById(id);
            return new ApiResponse<>(200, "Ingrediente excluído com sucesso!",
                    existIngredient.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
