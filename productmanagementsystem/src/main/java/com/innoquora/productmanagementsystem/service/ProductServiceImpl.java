package com.innoquora.productmanagementsystem.service;

import com.innoquora.productmanagementsystem.dto.request.ProductRequestDto;
import com.innoquora.productmanagementsystem.dto.response.ProductResponseDto;
import com.innoquora.productmanagementsystem.entity.Product;
import com.innoquora.productmanagementsystem.entity.User;
import com.innoquora.productmanagementsystem.exception.ProductNotFoundException;
import com.innoquora.productmanagementsystem.exception.UserNotFoundException;
import com.innoquora.productmanagementsystem.mapper.ProductMapper;
import com.innoquora.productmanagementsystem.repository.ProductRepository;
import com.innoquora.productmanagementsystem.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto, String email) {
        User user = getUser(email);
        return ProductMapper.toResponseDto(productRepository.save(ProductMapper.toEntity(dto, user)));
    }

    @Override
    public ProductResponseDto updateProductById(UUID id, ProductRequestDto dto, String email) {
        Product product = getProduct(id);
        product.setProductName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        return ProductMapper.toResponseDto(productRepository.save(product));
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(String productName, String category, String brand, int page, int size) {
        Specification<Product> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productName != null) predicates.add(cb.like(cb.lower(root.get("productName")), "%" + productName.toLowerCase() + "%"));
            if (category != null) predicates.add(cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() + "%"));
            if (brand != null) predicates.add(cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return productRepository.findAll(spec, PageRequest.of(page, size)).map(ProductMapper::toResponseDto);
    }

    @Override
    public ProductResponseDto getProductById(UUID id) {
        return ProductMapper.toResponseDto(getProduct(id));
    }

    @Override
    public void deleteProductById(UUID id, String email) {
        productRepository.delete(getProduct(id));
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
    }
}
