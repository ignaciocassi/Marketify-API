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

    //Servicio de dominio.
    //Actua como intermediario entre el controlador de la API (Product) y el repositorio (Producto).
    //Permite manipular el repositorio de Productos, utilizando términos del Dominio (Product).

    //Inyecta la interface productRepository y su implementación (ProductoRepository) en la capa de persistencia.
    //Efectivamente se instanciará un ProductoRepository
    //Podemos usar @Autowired porque aunque productRepository no es un componente, su implementación si lo es.
    @Autowired
    private ProductRepository productRepository;

    //Devuelve todos los Product.
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    //Devuelve un Product si existe el Producto en la capa de Persitencia, para ello consulta el productRepository.
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
