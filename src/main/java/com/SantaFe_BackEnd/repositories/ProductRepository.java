package com.SantaFe_BackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SantaFe_BackEnd.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
