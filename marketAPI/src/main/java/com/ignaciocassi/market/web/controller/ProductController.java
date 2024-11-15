package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.dto.ApiErrorResponse;
import com.ignaciocassi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(description = "Get all products.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No products not found.")
    })
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get a product by product ID.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "The id of the product.", required = true, example = "2")
            @PathVariable("id") int productId
    ) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    @Operation(description = "Get all products from a category by category ID.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No products were found for that category ID.")
    })
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "The id of the category.", required = true, example = "1")
            @PathVariable("category") int categoryId
    ) {
        return new ResponseEntity<>(productService.getByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/save")
    @Operation(description = "Save a product.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product successfully created."),
    })
    public ResponseEntity<Product> save(
            @RequestBody Product product
    ) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a product by pruduct ID.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Product not found."),
            @ApiResponse(responseCode = "409", description = "Product could not be deleted.")
    })
    public ResponseEntity delete(
            @Parameter(description = "The ID of the product.", required = true, example = "2")
            @PathVariable("id") int productId
    ) {
        productService.delete(productId);
        return new ResponseEntity<>(new ApiErrorResponse("El producto fue borrado exitosamente.",
                HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/scarce/{quantity}")
    @Operation(description = "Get products which have a stock below a specified amount.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No products found below the stock minimum.")
    })
    public ResponseEntity<List<Product>> getScarceProducts(
            @Parameter(description = "The minimum amount of the product.", required = true, example = "2")
            @PathVariable("quantity") int quantity
    ) {
        return new ResponseEntity<>(productService.getScarceProducts(quantity), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(description = "Get products by similar product name.",
            security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Product not found.")
    })
    public ResponseEntity<List<Product>> getProductsByName(
            @Parameter(description = "The name of the product to search.", required = true, example = "Lechuga")
            @PathVariable("name") String name
    ) {
        return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
    }

}
