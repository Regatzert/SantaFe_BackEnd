package com.SantaFe_BackEnd.controllers;

import java.util.stream.Collectors;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import java.nio.file.Path;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductController {

    @Autowired
    private ProductService productoService;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            @RequestParam("stock") Integer stock,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            Product producto = new Product();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);

            Product savedProduct = productoService.saveWithImage(producto, imagen);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al guardar la imagen"));
        }
    }

    @GetMapping(value = "/images/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
