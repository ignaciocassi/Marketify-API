package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Indica que es un controlador de una API REST.
@RestController
//Solicita el path para este controlador a partir del cual recibirá peticiones.
@RequestMapping("/products")
public class ProductController {

    //ProductControoler se encarga de recibir las peticiones en el path "/productos"
    //Utiliza el ProductService para realizar las tareas solicitadas en cada tipo de petición.

    //Inyecta el servicio ProductService.
    //Podemos usar @Autowired porque este tiene una anotación de componente @Service
    @Autowired
    private ProductService productService;

    //Indica que este método responderá al path "/productos/all"
    //Devuelve una Lista de Product mediante ProductService.
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    //Indica que este método responderá al path "/productos/{id}"
    //Devuelve un optional de Product mediante ProductService.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Indica que este método responderá al path "/productos/category/{category}"
    //Devuelve un optional de Lista de Product mediante ProductService.
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Indica que este método responderá al path "/productos/save"
    //Recibe un Product en el body y lo guarda como ProductoUutilizando ProductService. Finalmente lo devuelve.
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    //Indica que este método responderá al path "/productos/delete/{id}
    //Recibe un id y borra el Producto mediante ProductService, devuelve un booleano.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Indica que este método respondera al path "productos/scarce/{quantity}"
    //Devuelve un optional de lista de Product de los que tengan un stock menor a la cantidad dada.
    @GetMapping("/scarce/{quantity}")
    public Optional<List<Product>> getScarceProducts(@PathVariable("quantity") int quantity) {
        return productService.getScarceProducts(quantity);
    }

    //El nombre deberia ser unico, descartar.
    @GetMapping("/name/{name}")
    public Optional<Product> getProductByName(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }

    //El nombre debería ser único, descartar.
    @DeleteMapping("/deletebyname/{name}")
    public boolean delete(@PathVariable("name") String name) {
        return productService.delete(name);
    }

}
