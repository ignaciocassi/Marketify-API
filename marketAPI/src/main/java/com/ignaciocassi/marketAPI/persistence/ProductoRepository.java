package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.repository.ProductRepository;
import com.ignaciocassi.marketAPI.persistence.crud.ProductoCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import com.ignaciocassi.marketAPI.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//Repositorio de la capa de persistencia.
//Se encarga realizar las transferencias con la base de datos, utilizando productoCrudRepository (CRUD y C. Personaliz).
//Utiliza mapper para convertir de Entidad de Persistencia (Producto) que son devueltas por las operaciones de
//ProductoCrudRepository, a Entidad de dominio (Product).

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public Optional<List<Product>> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarce(int quantity) {
        List<Producto> productos = productoCrudRepository.findByCantidadStockLessThan(quantity);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId)
                .map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Optional<List<Product>> getProductByName(String name) {
        List<Producto> productos = productoCrudRepository.findByNombreContainingIgnoreCase(name);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }

}
