package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import com.SantaFe_BackEnd.entities.Almacen;

public interface AlmacenService {
    
    public List<Almacen> findAll();

    Optional<Almacen> findById(Long id);

    Almacen save(Almacen almacen);

    void deleteById(Long id);
}
