package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Category;
import com.ignaciocassi.market.domain.service.CategoryService;
import com.ignaciocassi.market.web.exceptions.CategoryNotFoundException;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Get all categories.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getAll() {
        return categoryService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a category by category ID.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Category not found.")
    })
    public ResponseEntity<Category> getCategory(@ApiParam(value = "The ID of the category.",
                                                    required = true, example = "1")@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get categories by similar category name", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getCategoryByName(@ApiParam(value = "The name of the category to search.",
                                            required = true, example = "Verdura") @PathVariable("name") String name) {
        return categoryService.getCategoryByName(name)
                .map(categories -> new ResponseEntity<>(categories, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));

    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a category.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category successfully created.")
    })
    public ResponseEntity<Category> save(@ApiParam(value = "The category must include a name and a status.")
                                             @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a category by category ID.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No category found.")
    })
    public ResponseEntity delete(@ApiParam(value = "The ID of the category.", required = true, example = "1")
                                     @PathVariable("id") int categoryId) {
        return new ResponseEntity(categoryService.delete(categoryId), HttpStatus.OK);
    }

    @PostMapping("/togglestatus/{id}")
    @ApiOperation(value = "Toggle a category's status.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No category found.")
    })
    public ResponseEntity<Boolean> toggleStatus(@ApiParam(value = "The ID of the category to toggle it's state.",
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
