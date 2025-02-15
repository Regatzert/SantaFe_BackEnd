package com.SantaFe_BackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    // @Autowired
    // private ProductService productService;

    // @GetMapping("/search")
    // public List<Product> searchProducts(@RequestParam("query") String query) {
    //     return productService.findByNameContainingIgnoreCase(query);
    // }
}
