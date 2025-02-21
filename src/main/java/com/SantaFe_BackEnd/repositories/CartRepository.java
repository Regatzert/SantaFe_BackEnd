package com.SantaFe_BackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SantaFe_BackEnd.models.ICartItem;

public interface CartRepository extends JpaRepository<ICartItem, Long>{
    
}
