package com.SantaFe_BackEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SantaFe_BackEnd.entities.User;
import com.SantaFe_BackEnd.models.UserRequest;

import io.micrometer.common.lang.NonNull;

public interface UserService {

    List<User> findAll();

    Page<User> findAll(Pageable pageable); //devuelve una lista de páginas de usuarios

    Optional<User> findById(@NonNull Long id);

    User save(User user);

    Optional<User> update (UserRequest user, Long id);


    void deleteById(Long id);
}
