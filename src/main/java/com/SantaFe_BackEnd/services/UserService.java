package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import com.SantaFe_BackEnd.entities.User;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
