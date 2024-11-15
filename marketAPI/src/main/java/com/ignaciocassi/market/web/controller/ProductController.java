package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.dto.ApiErrorResponse;
import com.ignaciocassi.market.domain.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId
    ) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") int categoryId
    ) {
        return new ResponseEntity<>(productService.getByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(
            @RequestBody Product product
    ) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId
    ) {
        productService.delete(productId);
        return new ResponseEntity<>(new ApiErrorResponse("El producto fue borrado exitosamente.",
                HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/scarce/{quantity}")
    public ResponseEntity<List<Product>> getScarceProducts(@PathVariable("quantity") int quantity
    ) {
        return new ResponseEntity<>(productService.getScarceProducts(quantity), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable("name") String name
    ) {
        return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
    }

}
