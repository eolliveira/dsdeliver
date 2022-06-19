package com.example.dsdeliver.Services;

import com.example.dsdeliver.dto.ProductDTO;
import com.example.dsdeliver.entities.Product;
import com.example.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameDesc();
        return list.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
    }

}
