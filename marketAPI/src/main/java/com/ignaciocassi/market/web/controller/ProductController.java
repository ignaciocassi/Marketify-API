package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.dto.ApiErrorResponse;
import com.ignaciocassi.market.domain.service.ProductService;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Get all products.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products not found.")
    })
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product by product ID.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "The id of the product.", required = true, example = "2")
            @PathVariable("id") int productId
    ) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = "Get all products from a category by category ID.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products were found for that category ID.")
    })
    public ResponseEntity<List<Product>> getByCategory(
            @ApiParam(value = "The id of the category.", required = true, example = "1")
            @PathVariable("category") int categoryId
    ) {
        return new ResponseEntity<>(productService.getByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a product.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product successfully created."),
    })
    public ResponseEntity<Product> save(
            @RequestBody Product product
    ) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a product by pruduct ID.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product successfully deleted."),
            @ApiResponse(code = 404, message = "Product not found."),
            @ApiResponse(code = 409, message = "Product could not be deleted.")
    })
    public ResponseEntity delete(
            @ApiParam(value = "The ID of the product.", required = true, example = "2")
            @PathVariable("id") int productId
    ) {
        productService.delete(productId);
        return new ResponseEntity<>(new ApiErrorResponse("El producto fue borrado exitosamente.",
                HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/scarce/{quantity}")
    @ApiOperation(value = "Get products which have a stock below a specified amount.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No products found below the stock minimum.")
    })
    public ResponseEntity<List<Product>> getScarceProducts(
            @ApiParam(value = "The minimum amount of the product.", required = true, example = "2")
            @PathVariable("quantity") int quantity
    ) {
        return new ResponseEntity<>(productService.getScarceProducts(quantity), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get products by similar product name.",
            authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Product not found.")
    })
    public ResponseEntity<List<Product>> getProductsByName(
            @ApiParam(value = "The name of the product to search.", required = true, example = "Lechuga")
            @PathVariable("name") String name
    ) {
        return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
    }

}
