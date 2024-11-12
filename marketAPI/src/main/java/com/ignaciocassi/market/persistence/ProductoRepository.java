package com.ignaciocassi.market.persistence;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.repository.ProductRepository;
import com.ignaciocassi.market.persistence.crud.ProductoCrudRepository;
import com.ignaciocassi.market.persistence.entities.Producto;
import com.ignaciocassi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//Repositorio de la capa de persistencia.
//Se encarga realizar las transferencias con la base de datos, utilizando productoCrudRepository (CRUD y C. Personaliz).
//Utiliza mapper para convertir de Entidad de Persistencia (Producto) que son devueltas por las operaciones de
//ProductoCrudRepository, a Entidad de dominio (Product).

@Repository
public class ProductoRepository implements ProductRepository {

    private final ProductoCrudRepository productoCrudRepository;

    private final ProductMapper mapper;

    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper mapper) {
        this.productoCrudRepository = productoCrudRepository;
        this.mapper = mapper;
    }

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
    public Optional<List<Product>> getProductsByNameContaining(String name) {
        List<Producto> productos = productoCrudRepository.findByNombreContainingIgnoreCase(name);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productoCrudRepository.findByNombreEquals(name)
                .map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public Integer deleteByIdProducto(int productId) {
        return productoCrudRepository.softDeleteByIdProducto(productId);
    }

}
