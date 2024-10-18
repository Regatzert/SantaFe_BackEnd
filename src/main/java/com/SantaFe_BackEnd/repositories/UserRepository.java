package com.SantaFe_BackEnd.repositories;

import org.springframework.data.repository.CrudRepository;

import com.SantaFe_BackEnd.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
