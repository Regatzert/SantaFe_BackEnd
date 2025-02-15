package com.SantaFe_BackEnd.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.SantaFe_BackEnd.entities.Visita;
import com.SantaFe_BackEnd.repositories.VisitaRepository;

@Service
public class VisitaService {

    private final VisitaRepository visitaRepository;

    public VisitaService(VisitaRepository visitaRepository) {
        this.visitaRepository = visitaRepository;
    }

    public void registrarVisita() {
        LocalDate hoy = LocalDate.now();
        Visita visita = visitaRepository.findByFecha(hoy).orElse(new Visita(hoy,0));
        visita.setCantidad(visita.getCantidad() + 1);
        visitaRepository.save(visita);
    }

    public List<Visita> obtenerVisita() {
        return visitaRepository.findAll();
    }
}
