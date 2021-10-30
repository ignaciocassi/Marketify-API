package com.ignaciocassi.marketAPI.domain.service;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<List<Product>> getAll() {
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
        return getProduct(productId)
                .map(product -> {
                    productRepository.delete(productId);
                    return true;
        }).orElse(false);
    }

    public Optional<List<Product>> getScarceProducts(int quantity) {
        return productRepository.getScarce(quantity);
    }

    public Optional<List<Product>> getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

}
