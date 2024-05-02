package com.pizzaria.apirestfull.model.dto;

import com.pizzaria.apirestfull.model.Customer;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome.")
    private String name;

    @NotBlank(message = "É necessário informar o número de telefone.")
    private String phoneNumber;

    @NotBlank(message = "É necessário informar o endereço.")
    private String address;

    @NotBlank(message = "É necessário informar o login.")
    private String login;

    @NotBlank(message = "É necessário informar a senha.")
    private String password;

    public CustomerDTO() {

    }

    public CustomerDTO(Long id) {
        this.id = id;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
        this.login = customer.getLogin();
        this.password = customer.getPassword();
    }

    public static Customer convert(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
        customer.setLogin(customerDTO.getLogin());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
