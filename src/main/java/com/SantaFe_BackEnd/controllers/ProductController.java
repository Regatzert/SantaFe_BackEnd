package com.SantaFe_BackEnd.controllers;

import java.util.stream.Collectors;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductController {

    @Autowired
    private ProductService productoService;

    @GetMapping
    public List<Product> list() {
        return productoService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Product> listPageable(@PathVariable Integer page) {
        PageRequest pageable = PageRequest.of(page, 10);
        return productoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Product> productOptional = productoService.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Collections.singletonMap("error", "El producto no se encontro por el id" + id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product producto, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product producto, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Product> productoOptional = productoService.update(producto, id);

        return productoOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Product> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = result.getFieldErrors().stream()
            .collect(Collectors.toMap(field -> field.getField(), field -> "El campo " + field.getField() + " " + field.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
