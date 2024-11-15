package com.ignaciocassi.market.web.controller;

import com.ignaciocassi.market.domain.Category;
import com.ignaciocassi.market.domain.service.CategoryService;
import com.ignaciocassi.market.web.exceptions.CategoryNotFoundException;
import com.ignaciocassi.market.web.exceptions.PurchaseNotFoundException;
import com.ignaciocassi.market.web.messages.ResponseStrings;
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
    public ResponseEntity<List<Category>> getAll() {
        return categoryService.getAll()
                .filter(purchases -> !purchases.isEmpty())
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElseThrow(() -> new PurchaseNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Category>> getCategoryByName(@PathVariable("name") String name) {
        return categoryService.getCategoryByName(name)
                .map(categories -> new ResponseEntity<>(categories, HttpStatus.OK))
                .orElseThrow(() -> new CategoryNotFoundException(ResponseStrings.NO_CATEGORIES_FOUND));

    }

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int categoryId) {
        return new ResponseEntity(categoryService.delete(categoryId), HttpStatus.OK);
    }

    @PostMapping("/togglestatus/{id}")
    public ResponseEntity<Boolean> toggleStatus(@PathVariable("id") int categoryId) {
        Optional<Category> category = categoryService.getCategory(categoryId);
        if (category.isPresent()) {
            categoryService.toggleStatus(category.get().getCategoryId());
            return new ResponseEntity<>(!category.get().getActive(),HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
    }

}
