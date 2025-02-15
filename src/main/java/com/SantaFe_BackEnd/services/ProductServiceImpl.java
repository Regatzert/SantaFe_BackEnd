package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Product> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNameContainingIgnoreCase'");
    }
    
    // @Autowired
    // ProductRepository productRepository;

    // @Override
    // @Transactional(readOnly = true)
    // public List<Product> findAll() {
    //     return (List<Product>) this.productRepository.findAll();
        
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public Optional<Product> findById(Long id) {
    //     return this.productRepository.findById(id);
    // }

    // @Override
    // @Transactional
    // public Product save(Product product) {
    //     return productRepository.save(product);
    // }

    // @Override
    // @Transactional
    // public void deleteById(Long id) {
    //     productRepository.deleteById(id);
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public List<Product> findByNameContainingIgnoreCase(String name) {
    //     return productRepository.findByNameContainingIgnoreCase(name);
    // }

}
