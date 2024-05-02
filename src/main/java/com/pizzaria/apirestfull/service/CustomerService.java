package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Customer;
import com.pizzaria.apirestfull.model.dto.CustomerDTO;
import com.pizzaria.apirestfull.repository.CustomerRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ApiResponse<CustomerDTO> create(CustomerDTO customerDTO) {
        try {
            Customer createdCustomer = CustomerDTO.convert(customerDTO);
            createdCustomer = this.customerRepository.save(createdCustomer);
            return new ApiResponse<>(201, "Cliente cadastrado com sucesso!",
                    new CustomerDTO(createdCustomer));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<CustomerDTO>> findAll() {
        try {
            List<Customer> foundAllCustomer = this.customerRepository.findAll();
            return new ApiResponse<>(200, "Listagem de clientes realizada com sucesso!",
                    foundAllCustomer.stream().map(CustomerDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<CustomerDTO> findOne(Long id) {
        try {
            Optional<Customer> foundOneCustomer = this.customerRepository.findById(id);

            return foundOneCustomer.map(customer -> new ApiResponse<>(200,"Detalhamento de cliente realizado com sucesso!",
                    new CustomerDTO(customer))).orElseGet(() -> new ApiResponse<>(204,
                    "Cliente não encontrado!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<CustomerDTO> update(Long id, CustomerDTO customerDTO) {
        try {
            ApiResponse<CustomerDTO> existCustomer = this.findOne(id);

            if (existCustomer.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Esse cliente não existe!", null);
            }

            Customer updatedCustomer = CustomerDTO.convert(customerDTO);
            updatedCustomer.setId(id);
            updatedCustomer = this.customerRepository.save(updatedCustomer);

            return new ApiResponse<>(200, "Cliente editado com sucesso!",
                    new CustomerDTO(updatedCustomer));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<CustomerDTO> delete(Long id) {
        try {
            ApiResponse<CustomerDTO> existCustomer = this.findOne(id);
            if (existCustomer.getStatus() != 200) {
                return new ApiResponse<>(400, "Cliente não encontrado! Não foi possível" +
                        "excluir registro", null);
            }

            this.customerRepository.deleteById(id);
            return new ApiResponse<>(200, "Cliente excluído com sucesso!",
                    existCustomer.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
