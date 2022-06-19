package com.example.dsdeliver.dto;

import com.example.dsdeliver.entities.Product;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUri;

    public ProductDTO(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUri = entity.getImageUri();
    }
}
