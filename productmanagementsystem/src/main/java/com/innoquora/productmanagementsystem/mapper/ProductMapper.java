package com.innoquora.productmanagementsystem.mapper;

import com.innoquora.productmanagementsystem.dto.request.ProductRequestDto;
import com.innoquora.productmanagementsystem.dto.response.ProductResponseDto;
import com.innoquora.productmanagementsystem.entity.Product;
import com.innoquora.productmanagementsystem.entity.User;

public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto, User user) {
        return Product.builder()
                .productName(dto.getProductName())
                .category(dto.getCategory())
                .brand(dto.getBrand())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .user(user)
                .build();
    }

    public static ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .addedBy(product.getUser().getName())
                .build();
    }
}
