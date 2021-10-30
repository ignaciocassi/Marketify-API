package com.ignaciocassi.marketAPI.web.controller;

import com.ignaciocassi.marketAPI.domain.Category;
import com.ignaciocassi.marketAPI.domain.service.CategoryService;
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
    @ApiOperation("Get all categories.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getAll() {
        return categoryService.getAll()
                .map(categories -> new ResponseEntity<>(categories, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a category by category ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "Category not found.")
    })
    public ResponseEntity<Category> getCategory(@ApiParam(value = "The ID of the category.", required = true, example = "1")@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    @ApiOperation("Get categories by similar product name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No categories were found.")
    })
    public ResponseEntity<List<Category>> getCategoryByName(@ApiParam(value = "The name of the category to search.", required = true, example = "Verdura") @PathVariable("name") String name) {
        Optional<List<Category>> categories = categoryService.getCategoryByName(name);
        if (!categories.get().isEmpty()) {
            return new ResponseEntity<>(categories.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    @ApiOperation("Save a category.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category successfully created."),
            @ApiResponse(code = 400, message = "The category must include active and a name.")
    })
    public ResponseEntity<Category> save(@ApiParam(value = "The category must include a name and a status.")@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a category by category ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No category found.")
    })
    public ResponseEntity delete(@ApiParam(value = "The ID of the category.", required = true, example = "1")@PathVariable("id") int categoryId) {
        if (categoryService.delete(categoryId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/togglestatus/{id}")
    @ApiOperation("Toggle a category's status.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 404, message = "No category found.")
    })
    public ResponseEntity<Boolean> toggleStatus(@ApiParam(value = "The ID of the category to toggle it's state.", required = true, example = "1")@PathVariable("id") int categoryId) {
        Optional<Category> category = categoryService.getCategory(categoryId);
        if (category.isPresent()) {
            categoryService.toggleStatus(category.get().getCategoryId());
            return new ResponseEntity<>(!category.get().getActive(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
