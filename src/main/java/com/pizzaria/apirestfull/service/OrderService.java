package com.pizzaria.apirestfull.service;

import com.pizzaria.apirestfull.model.Batch;
import com.pizzaria.apirestfull.model.Customer;
import com.pizzaria.apirestfull.model.Order;
import com.pizzaria.apirestfull.model.dto.BatchDTO;
import com.pizzaria.apirestfull.model.dto.CustomerDTO;
import com.pizzaria.apirestfull.model.dto.OrderDTO;
import com.pizzaria.apirestfull.repository.BatchRepository;
import com.pizzaria.apirestfull.repository.CustomerRepository;
import com.pizzaria.apirestfull.repository.OrderRepository;
import com.pizzaria.apirestfull.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BatchService batchService;

    public ApiResponse<OrderDTO> create(OrderDTO orderDTO) {
        try {
            Customer existCustomer = CustomerDTO.convert(
                    this.customerService.findOne(orderDTO.getIdCustomer()).getData());
            Batch existBatch = BatchDTO.convert(
                    this.batchService.findOne(orderDTO.getIdBatch()).getData());

            Order createdOrder = OrderDTO.convert(orderDTO, existCustomer, existBatch);
            createdOrder = this.orderRepository.save(createdOrder);
            return new ApiResponse<>(201, "Pedido cadastrado com sucesso!", new OrderDTO(createdOrder));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<List<OrderDTO>> findAll() {
        try {
            List<Order> foundAllOrder = this.orderRepository.findAll();
            return new ApiResponse<>(200, "Listagem de pedidos realizada com sucesso!",
                    foundAllOrder.stream().map(OrderDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<OrderDTO> findOne(Long id) {
        try {
            Optional<Order> foundOneOrder = this.orderRepository.findById(id);

            return foundOneOrder.map(order -> new ApiResponse<>(200, "Detalhamento de " +
                    "pedido" +
                    " realizado com sucesso!",
                    new OrderDTO(order))).orElseGet(() -> new ApiResponse<>(204,"Pedido" +
                    " não encontrado!", null));

        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<OrderDTO> update(Long id, OrderDTO orderDTO) {
        try {
            ApiResponse<OrderDTO> existOrder = this.findOne(id);

            if (existOrder.getStatus() != 200) {
                return new ApiResponse<>(404, "ID não é válido! Esse pedido não " +
                        "existe!", null);
            }

            Customer existCustomer =
                    CustomerDTO.convert(this.customerService.findOne(orderDTO.getIdCustomer()).getData());
            Batch existBatch =
                    BatchDTO.convert(this.batchService.findOne(orderDTO.getIdBatch()).getData());

            Order updatedOrder = OrderDTO.convert(orderDTO, existCustomer, existBatch);

            updatedOrder.setId(id);
            updatedOrder = this.orderRepository.save(updatedOrder);

            return new ApiResponse<>(200, "Pedido editado com sucesso!",
                    new OrderDTO(updatedOrder));
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }

    public ApiResponse<OrderDTO> delete(Long id) {
        try {
            ApiResponse<OrderDTO> existOrder = this.findOne(id);
            if (existOrder.getStatus() != 200) {
                return new ApiResponse<>(400, "Pedido não encontrado! Não foi possível " +
                        "excluir registro", null);
            }

            this.orderRepository.deleteById(id);
            return new ApiResponse<>(200, "Peiddo excluído com sucesso!",
                    existOrder.getData());
        } catch (Exception e) {
            return new ApiResponse<>(500, e.getMessage(), null);
        }
    }
}
