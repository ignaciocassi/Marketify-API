package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Category;
import com.ignaciocassi.market.domain.service.CategoryService;
import com.ignaciocassi.market.web.exceptions.CategoryNotFoundException;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    @Operation(description = "Get all categories.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getAll() {
        return categoryService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));
    }

    @GetMapping("/{id}")
    @Operation(description = "Get a category by category ID.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "Category not found.")
    })
    public ResponseEntity<Category> getCategory(@Parameter(description = "The ID of the category.",
                                                    required = true, example = "1")@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    @Operation(description = "Get categories by similar category name", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getCategoryByName(@Parameter(description = "The name of the category to search.",
                                            required = true, example = "Verdura") @PathVariable("name") String name) {
        return categoryService.getCategoryByName(name)
                .map(categories -> new ResponseEntity<>(categories, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));

    }

    @PostMapping("/save")
    @Operation(description = "Save a category.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category successfully created.")
    })
    public ResponseEntity<Category> save(@Parameter(description = "The category must include a name and a status.")
                                             @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a category by category ID.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No category found.")
    })
    public ResponseEntity delete(@Parameter(description = "The ID of the category.", required = true, example = "1")
                                     @PathVariable("id") int categoryId) {
        return new ResponseEntity(categoryService.delete(categoryId), HttpStatus.OK);
    }

    @PostMapping("/togglestatus/{id}")
    @Operation(description = "Toggle a category's status.", security = { @SecurityRequirement(name = "JWT") })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "404", description = "No category found.")
    })
    public ResponseEntity<Boolean> toggleStatus(@Parameter(description = "The ID of the category to toggle it's state.",
                                                required = true, example = "1")@PathVariable("id") int categoryId) {
        Optional<Category> category = categoryService.getCategory(categoryId);
        if (category.isPresent()) {
            categoryService.toggleStatus(category.get().getCategoryId());
            return new ResponseEntity<>(!category.get().getActive(),HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
    }

}
