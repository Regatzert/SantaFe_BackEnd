package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;
import com.SantaFe_BackEnd.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

     List<Product> findAll();
     Page<Product> findAll(Pageable pageable);
     Optional<Product> findById(Long id);
     Product save(Product producto);
     Optional<Product> update(Product producto, Long id);
     void deleteById(Long id);
}
