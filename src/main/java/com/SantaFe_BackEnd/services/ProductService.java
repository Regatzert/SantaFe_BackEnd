package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import com.SantaFe_BackEnd.entities.Product;

public interface ProductService {

    public List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);
}
