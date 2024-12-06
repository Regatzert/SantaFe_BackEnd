package com.SantaFe_BackEnd.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.SantaFe_BackEnd.entities.User;
import com.SantaFe_BackEnd.services.UserService;

import jakarta.validation.Valid;

import java.lang.StackWalker.Option;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<User> listPageable(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userService.findAll(pageable);
    }

    @GetMapping("/id")
    public ResponseEntity<?> listId(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Collections.singletonMap("error", "El usuario no se encontro por el id" + id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    
    
    @PutMapping("path/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<User> userOptional = userService.findById(id);
        
        if (userOptional.isPresent()) {
            User userDB = userOptional.get();
            userDB.setEmail(user.getEmail());
            userDB.setLastname(user.getLastname());
            userDB.setName(user.getName());
            userDB.setUsername(user.getUsername());
            userDB.setPassword(user.getPassword());

            return ResponseEntity.ok(userService.save(userDB));
        }

        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo "+error.getField()+ error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}


