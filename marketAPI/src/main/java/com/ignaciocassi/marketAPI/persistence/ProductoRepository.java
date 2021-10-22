package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.repository.ProductRepository;
import com.ignaciocassi.marketAPI.persistence.crud.ProductoCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import com.ignaciocassi.marketAPI.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    //Para orientar el ProductoRepository al dominio, debemos implementar la interface ProductRepository.
    //Se encarga de interactuar con la base de datos.
    //Usa el productoCrudRepository, el cual define el comportamiento personalizado, además del CRUD proveído
    //por el repository CrudRepository, del cual hereda.
    //Adicionalmente utiliza ProductMapper para convertir de Producto a Product y viceversa.
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        //Obtiene la lista de Productos de la base de datos, y la convierte a lista de Product mediante mapper.
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productoCrudRepository.findByNombre(name).map(producto -> mapper.toProduct(producto));
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

    @Override
    public void delete(String name) {
        productoCrudRepository.deleteByNombre(name);
    }

}