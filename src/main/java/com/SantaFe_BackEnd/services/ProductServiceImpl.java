package com.SantaFe_BackEnd.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.SantaFe_BackEnd.entities.Product;
import com.SantaFe_BackEnd.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productoRepository;

    private static final String UPLOAD_DIR = "../../../../../uploads/";

    @Override
@Transactional
public Product saveWithImage(Product producto, MultipartFile imagen) throws IOException {
    if (imagen != null && !imagen.isEmpty()) {
        try {
            String fileName = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            
            // Crear la carpeta si no existe
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            Files.copy(imagen.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            producto.setImagen_url(fileName);
            System.out.println("Imagen guardada en: " + filePath.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();  // Muestra el error exacto en la consola
            throw new IOException("No se pudo guardar la imagen: " + e.getMessage());
        }
    }
    return productoRepository.save(producto);
}


    @Override
    @Transactional
    public List<Product> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Product> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Optional<Product> update(Product producto, Long id) {
        Optional<Product> productoOptional = productoRepository.findById(id);

        if (productoOptional.isPresent()) {
            Product productoDB = productoOptional.get();
            productoDB.setNombre(producto.getNombre());
            productoDB.setDescripcion(producto.getDescripcion());
            productoDB.setPrecio(producto.getPrecio());
            productoDB.setImagen_url(producto.getImagen_url());
            return Optional.of(productoRepository.save(productoDB));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

}
