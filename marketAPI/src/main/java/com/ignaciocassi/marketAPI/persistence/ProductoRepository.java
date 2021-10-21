package com.ignaciocassi.marketAPI.persistence;

import com.ignaciocassi.marketAPI.persistence.crud.ProductoCrudRepository;
import com.ignaciocassi.marketAPI.persistence.entities.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    //Se encarga de interactuar con la base de datos.

    //Usa el productoCrudRepository, el cual define el comportamiento personalizado, además del CRUD proveído
    // por el repository CrudRepository, del cual hereda.
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(Integer idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProductoById(int id) {
        return productoCrudRepository.findById(id);
    }

    public Optional<Producto> getProductoByNombre(String nombre) {
        return productoCrudRepository.findByNombre(nombre);
    }

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void delete(Integer idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

    public void delete(String nombre) {
        productoCrudRepository.deleteByNombre(nombre);
    }

}
