package com.example.dsdeliver.Services;

import com.example.dsdeliver.Services.exceptions.ResourceNotFoundException;
import com.example.dsdeliver.dto.OrderDTO;
import com.example.dsdeliver.dto.ProductDTO;
import com.example.dsdeliver.entities.Order;
import com.example.dsdeliver.entities.Product;
import com.example.dsdeliver.enums.OrderStatus;
import com.example.dsdeliver.repositories.OrderRepository;
import com.example.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findAll();
        return list.stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setAddress(dto.getAddress());
        order.setLatitude(dto.getLatitude());
        order.setLongitude(dto.getLongitude());
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.PENDING);

        for (ProductDTO p : dto.getProducts()) {
            try {
                Product product = productRepository.getReferenceById(p.getId());
                order.getProducts().add(product);
            }
            catch (EntityNotFoundException e){
                throw new ResourceNotFoundException("Product id: " + p.getId() + " not found");
            }
        }

        order = repository.save(order);
        return new OrderDTO(order);
    }

}
