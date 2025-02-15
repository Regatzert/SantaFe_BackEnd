package com.SantaFe_BackEnd.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SantaFe_BackEnd.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    Optional<Visita> findByFecha(LocalDate fecha);
}
