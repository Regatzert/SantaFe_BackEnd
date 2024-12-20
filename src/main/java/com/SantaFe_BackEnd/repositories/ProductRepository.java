package com.SantaFe_BackEnd.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SantaFe_BackEnd.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String name);
}
