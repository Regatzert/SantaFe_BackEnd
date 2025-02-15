package com.SantaFe_BackEnd.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SantaFe_BackEnd.entities.Visita;
import com.SantaFe_BackEnd.services.VisitaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/visitas")
@CrossOrigin(origins = "*")
public class VisitaController {

    private final VisitaService visitaService;

    public VisitaController(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @PostMapping("/registrar")
    public void registarVisita() {
        visitaService.registrarVisita();
    }

    @GetMapping
    public List<Visita> getVisitas() {
        return visitaService.obtenerVisita();
    }
    
    
}
