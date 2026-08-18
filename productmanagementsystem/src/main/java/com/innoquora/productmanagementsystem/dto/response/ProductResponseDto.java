package com.innoquora.productmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private UUID id;
    private String productName;
    private String category;
    private String brand;
    private Double price;
    private Integer quantity;
    private String addedBy;
}
