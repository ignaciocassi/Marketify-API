package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Servicio utilizado para implementar lógica de negocio y para desacoplar la capa de dominio de la capa de persistencia.
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        // TODO: Validar si el producto ya existe antes de guardarlo
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        // TODO: Validar si el producto fue comprado antes de intentar eliminarlo
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
