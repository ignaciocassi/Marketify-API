package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Category;
import com.ignaciocassi.marketAPI.domain.service.CategoryService;
import com.ignaciocassi.marketAPI.web.exceptions.CategoryNotFoundException;
import com.ignaciocassi.marketAPI.web.exceptions.NoCategoriesListedException;
import com.ignaciocassi.marketAPI.web.messages.ResponseStrings;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    @ApiOperation(value = "Get all categories.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getAll() {
        Optional<List<Category>> categories = categoryService.getAll();
        if (!categories.get().isEmpty()) {
            return new ResponseEntity<>(categories.get(), HttpStatus.OK);
        } else {
            throw new NoCategoriesListedException(ResponseStrings.NO_CATEGORIES_LISTED);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a category by category ID.", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Category not found.")
    })
    public ResponseEntity<Category> getCategory(@ApiParam(value = "The ID of the category.",
                                                    required = true, example = "1")@PathVariable("id") int categoryId) {
        Optional<Category> category = categoryService.getCategory(categoryId);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get categories by similar category name", authorizations = { @Authorization(value="JWT") })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getCategoryByName(@ApiParam(value = "The name of the category to search.",
                                            required = true, example = "Verdura") @PathVariable("name") String name) {
        Optional<List<Category>> categories = categoryService.getCategoryByName(name);
        if (!categories.get().isEmpty()) {
            return new ResponseEntity<>(categories.get(),HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND);
        }
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
        if (categoryService.delete(categoryId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
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
