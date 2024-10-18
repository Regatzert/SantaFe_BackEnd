package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SantaFe_BackEnd.entities.Almacen;
import com.SantaFe_BackEnd.repositories.AlmacenRepository;

@Service
public class AlmacenServiceImpl implements AlmacenService{

    @Autowired
    AlmacenRepository almacenRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Almacen> findAll() {
        return (List<Almacen>) this.almacenRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Almacen> findById(Long id) {
        return this.almacenRepository.findById(id);
    }

    @Override
    @Transactional
    public Almacen save(Almacen almacen) {
        return this.almacenRepository.save(almacen);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        almacenRepository.deleteById(id);
    }
    
}
