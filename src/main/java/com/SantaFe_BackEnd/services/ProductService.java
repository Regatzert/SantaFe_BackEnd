package com.SantaFe_BackEnd.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.repositories.ProductRepository;

@Service
public class ProductService {

     private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
