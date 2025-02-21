package com.SantaFe_BackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save-producto")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product, 
                                            @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        System.out.println("Producto recibido: " + product); // 🔥 Verifica que los datos llegan bien

        try {
            Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
