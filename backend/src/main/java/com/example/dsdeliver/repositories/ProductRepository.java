package com.example.dsdeliver.repositories;

import com.example.dsdeliver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p order by p.name DESC")
    List<Product> findAllByOrderByNameDesc();
}
