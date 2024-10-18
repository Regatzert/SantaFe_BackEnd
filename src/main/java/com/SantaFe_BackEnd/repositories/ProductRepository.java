package com.SantaFe_BackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.SantaFe_BackEnd.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
