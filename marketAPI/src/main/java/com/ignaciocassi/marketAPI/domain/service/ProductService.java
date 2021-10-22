package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Component es más general, pero service tiene una diferenciación semántica, de que forma parte de la lógica de negocio.
@Service
public class ProductService {
    //Actua como intermediario entre el controlador de la API y el repositorio.

    @Autowired //Se llama directamente a la interfaz, es un componente por ProductoRepository, que la implementa.
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

    public Optional<List<Product>> getScarceProducts(int quantity) {
        return productRepository.getScarceProducts(quantity);
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    public boolean delete(String name) {
        if (productRepository.getProductByName(name).isPresent()) {
            productRepository.delete(name);
            return true;
        } else {
            return false;
        }
    }

}
