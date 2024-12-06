package com.SantaFe_BackEnd.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SantaFe_BackEnd.entities.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    Page<User> findAll(Pageable pageable); //devuelve una lista de páginas de usuarios

    Optional<User>findByUsername(String username);
}
