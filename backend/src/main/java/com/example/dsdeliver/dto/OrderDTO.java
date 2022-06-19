package com.example.dsdeliver.dto;

import com.example.dsdeliver.entities.Order;
import com.example.dsdeliver.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;

    List<ProductDTO> products = new ArrayList<>();

    public OrderDTO(Order entity){
        this.id = entity.getId();
        this.address = entity.getAddress();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        entity.getProducts().stream().map(p -> products.add(new ProductDTO(p))).collect(Collectors.toList());
    }
}
