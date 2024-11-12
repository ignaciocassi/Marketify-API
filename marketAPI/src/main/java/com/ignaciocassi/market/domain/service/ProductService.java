package com.ignaciocassi.market.domain.service;

import com.ignaciocassi.market.domain.Product;
import com.ignaciocassi.market.domain.repository.ProductRepository;
import com.ignaciocassi.market.web.exceptions.*;
import com.ignaciocassi.market.web.messages.ResponseStrings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final PurchaseService purchaseService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, PurchaseService purchaseService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.purchaseService = purchaseService;
    }

    public List<Product> getAll() {
        logger.info("Fetching all products");
        return productRepository.getAll()
                .filter(products -> !products.isEmpty())
                .orElseThrow(() -> {
                    logger.info("No products listed");
                    return new NoProductsListedException(ResponseStrings.NO_PRODUCTS_LISTED);
                });
    }

    public Product getProduct(int productId) {
        logger.info("Fetching product with ID: {}", productId);
        return productRepository.getProduct(productId)
                .orElseThrow(() -> {
                    logger.warn("Product not found with ID: {}", productId);
                    return new ProductNotFoundException(ResponseStrings.PRODUCT_NOT_FOUND);
                });
    }

    public List<Product> getByCategory(int categoryId) {
        logger.info("Fetching products by category ID: {}", categoryId);
        if (categoryService.getCategoryById(categoryId).isEmpty()) {
            logger.warn("Category not found with ID: {}", categoryId);
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
        return productRepository.getByCategory(categoryId)
                .orElseThrow(() -> {
                    logger.warn("No products found in category ID: {}", categoryId);
                    return new CategoryNotFoundException(ResponseStrings.NO_PRODUCTS_IN_CATEGORY);
                });
    }

    public Product save(Product product) {
        logger.info("Saving product: {}", product.getName());
        if (categoryService.getCategoryById(product.getCategoryId()).isEmpty()) {
            logger.warn("Category not found with ID: {}", product.getCategoryId());
            throw new CategoryNotFoundException(ResponseStrings.CATEGORY_NOT_FOUND);
        }
        if (productRepository.getProductByName(product.getName()).isPresent()) {
            logger.warn("Product already exists with name: {}", product.getName());
            throw new ProductAlreadyExistsException(ResponseStrings.PRODUCT_ALREADY_EXISTS);
        }
        return productRepository.save(product);
    }

    @Transactional
    public Integer delete(int productId) {
        logger.info("Deleting product with ID: {}", productId);
        Optional<Product> product = productRepository.getProduct(productId);
        if (product.isEmpty()) {
            logger.warn("Product not found with ID: {}", productId);
            throw new ProductNotFoundException(ResponseStrings.PRODUCT_NOT_FOUND);
        }
        if (!purchaseService.getByContainingProduct(productId).get().isEmpty()) {
            logger.warn("Product cannot be deleted, it is associated with purchases: {}", productId);
            throw new ProductDeleteException(ResponseStrings.PRODUCT_CANNOT_BE_DELETED);
        }
        if (!product.get().isActive()) {
            logger.warn("Product is already deleted: {}", productId);
            throw new ProductDeleteException(ResponseStrings.PRODUCT_IS_DELETED);
        }
        return productRepository.deleteByIdProducto(productId);
    }

    public List<Product> getScarceProducts(int quantity) {
        logger.info("Fetching products with stock less than: {}", quantity);
        return productRepository.getScarce(quantity)
                .orElseThrow(() -> {
                    logger.warn("No products found with stock less than: {}", quantity);
                    return new NoProductsListedException(ResponseStrings.NO_SCARCE_PRODUCTS);
                });
    }

    public List<Product> getProductsByName(String name) {
        logger.info("Fetching products with name containing: {}", name);
        return productRepository.getProductsByNameContaining(name)
                .orElseThrow(() -> {
                    logger.warn("No products found with name containing: {}", name);
                    return new ProductNotFoundException(ResponseStrings.NO_PRODUCTS_FOUND);
                });
    }

}
