package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Drink;
import com.pizzaria.apirestfull.model.dto.DrinkDTO;
import com.pizzaria.apirestfull.repository.DrinkRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    public ApiResponse<DrinkDTO> create(DrinkDTO drinkDTO) {
        try {
            Drink createdDrink = DrinkDTO.convert(drinkDTO);
            createdDrink = this.drinkRepository.save(createdDrink);
            return new ApiResponse<>(201, "Bebida cadastrada com sucesso!",
                    new DrinkDTO(createdDrink));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<DrinkDTO>> findAll() {
        try {
            List<Drink> foundAllDrink = this.drinkRepository.findAll();
            return new ApiResponse<>(200, "Listagem de bebidas realizada com sucesso!",
                    foundAllDrink.stream().map(DrinkDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<DrinkDTO> findOne(Long id) {
        try {
            Optional<Drink> foundOneDrink = this.drinkRepository.findById(id);

            return foundOneDrink.map(drink -> new ApiResponse<>(200, "Detalhamento de bebida realizado com sucesso!",
                    new DrinkDTO(drink))).orElseGet(() -> new ApiResponse<>(204,"Bebida não encontrada!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<DrinkDTO> update(Long id, DrinkDTO drinkDTO) {
        try {
            ApiResponse<DrinkDTO> existDrink = this.findOne(id);

            if (existDrink.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Essa bebida não " +
                        "existe!", null);
            }

            Drink updatedDrink = DrinkDTO.convert(drinkDTO);
            updatedDrink.setId(id);
            updatedDrink = this.drinkRepository.save(updatedDrink);

            return new ApiResponse<>(200, "Bebida editada com sucesso!",
                    new DrinkDTO(updatedDrink));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<DrinkDTO> delete(Long id) {
        try {
            ApiResponse<DrinkDTO> existDrink = this.findOne(id);
            if (existDrink.getStatus() != 200) {
                return new ApiResponse<>(400, "Bebida não encontrada! Não foi possível " +
                        "excluir registro", null);
            }

            this.drinkRepository.deleteById(id);
            return new ApiResponse<>(200, "Bebida excluída com sucesso!",
                    existDrink.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
