package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Pizza;
import com.pizzaria.apirestfull.model.dto.PizzaDTO;
import com.pizzaria.apirestfull.repository.PizzaRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public ApiResponse<PizzaDTO> create(PizzaDTO pizzaDTO) {
        try {
            Pizza createdPizza = PizzaDTO.convert(pizzaDTO);
            createdPizza = this.pizzaRepository.save(createdPizza);
            return new ApiResponse<>(201, "Pizza cadastrada com sucesso!",
                    new PizzaDTO(createdPizza));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<PizzaDTO>> findAll() {
        try {
            List<Pizza> foundAllPizza = this.pizzaRepository.findAll();
            return new ApiResponse<>(200, "Listagem de pizza realizada com sucesso!",
                    foundAllPizza.stream().map(PizzaDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PizzaDTO> findOne(Long id) {
        try {
            Optional<Pizza> foundOnePizza = this.pizzaRepository.findById(id);

            return foundOnePizza.map(pizza -> new ApiResponse<>(200, "Detalhamento de " +
                    "pizza realizado com sucesso!",
                    new PizzaDTO(pizza))).orElseGet(() -> new ApiResponse<>(204,"Pizza " +
                    "não encontrada!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PizzaDTO> update(Long id, PizzaDTO pizzaDTO) {
        try {
            ApiResponse<PizzaDTO> existPizza = this.findOne(id);

            if (existPizza.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Essa pizza não " +
                        "existe!", null);
            }

            Pizza updatedPizza = PizzaDTO.convert(pizzaDTO);
            updatedPizza.setId(id);
            updatedPizza = this.pizzaRepository.save(updatedPizza);

            return new ApiResponse<>(200, "Pizza editada com sucesso!",
                    new PizzaDTO(updatedPizza));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<PizzaDTO> delete(Long id) {
        try {
            ApiResponse<PizzaDTO> existPizza = this.findOne(id);
            if (existPizza.getStatus() != 200) {
                return new ApiResponse<>(400, "Pizza não encontrada! Não foi possível " +
                        "excluir registro", null);
            }

            this.pizzaRepository.deleteById(id);
            return new ApiResponse<>(200, "Pizza excluída com sucesso!",
                    existPizza.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
