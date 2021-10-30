package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Product;
import com.ignaciocassi.marketAPI.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all products.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products not found.")
    })
    public ResponseEntity<List<Product>> getAll() {
        return productService.getAll()
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a product by product ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product.", required = true, example = "2") @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{category}")
    @ApiOperation("Get all products from a category by category ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products were found for that category ID.")
    })
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "The id of the category.", required = true, example = "1") @PathVariable("category") int categoryId) {
        Optional<List<Product>> products = productService.getByCategory(categoryId);
        if (!products.get().isEmpty()) {
            return new ResponseEntity<>(products.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    @ApiOperation("Save a product.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product successfully created."),
    })
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a product by pruduct ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    public ResponseEntity delete(@ApiParam(value = "The ID of the product.",required = true, example = "2")@PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/scarce/{quantity}")
    @ApiOperation("Get products which have a stock below a specified amount.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products found below the stock minimum.")
    })
    public ResponseEntity<List<Product>> getScarceProducts(@ApiParam(value = "The minimum amount of the product.",required = true, example = "2")@PathVariable("quantity") int quantity) {
        Optional<List<Product>> escasos = productService.getScarceProducts(quantity);
        if (!escasos.get().isEmpty()) {
            return new ResponseEntity<>(escasos.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    @ApiOperation("Get products by similar product name.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    public ResponseEntity<List<Product>> getProductByName(@ApiParam(value = "The name of the product to search.",required = true, example = "Lechuga")@PathVariable("name") String name) {
        return productService.getProductByName(name)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
