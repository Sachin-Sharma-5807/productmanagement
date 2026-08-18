package com.innoquora.productmanagementsystem.controller;

import com.innoquora.productmanagementsystem.dto.request.ProductRequestDto;
import com.innoquora.productmanagementsystem.dto.response.ProductResponseDto;
import com.innoquora.productmanagementsystem.payload.ApiResponse;
import com.innoquora.productmanagementsystem.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Auth")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductResponseDto>> addProduct(
            @Valid @RequestBody ProductRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product added successfully", productService.addProduct(dto, userDetails.getUsername())));
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProductById(
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Product updated successfully",
                productService.updateProductById(id, dto, userDetails.getUsername())));
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success("Product fetched successfully", productService.getProductById(id)));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ApiResponse<Page<ProductResponseDto>>> getAllProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success("Products fetched successfully",
                productService.getAllProducts(productName, category, brand, page, size)));
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetails userDetails) {
        productService.deleteProductById(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully", null));
    }
}
