package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productoRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Product> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Product> update(Product producto, Long id) {
        Optional<Product> productoOptional = productoRepository.findById(id);

        if (productoOptional.isPresent()) {
            Product productoDB = productoOptional.get();
            productoDB.setNombre(producto.getNombre());
            productoDB.setDescripcion(producto.getDescripcion());
            productoDB.setPrecio(producto.getPrecio());
            productoDB.setImagenUrl(producto.getImagenUrl());
            return Optional.of(productoRepository.save(productoDB));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

}
