package com.innoquora.productmanagementsystem.service;

import com.innoquora.productmanagementsystem.dto.request.ProductRequestDto;
import com.innoquora.productmanagementsystem.dto.response.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto dto, String email);

    ProductResponseDto updateProductById(UUID id, ProductRequestDto dto, String email);

    Page<ProductResponseDto> getAllProducts(String productName, String category, String brand, int page, int size);

    ProductResponseDto getProductById(UUID id);

    void deleteProductById(UUID id, String email);
}