package com.example.product.controller;

import com.example.product.common.EntityResponse;
import com.example.product.model.request.AddOrEditProductRequest;
import com.example.product.model.request.PageResponseModel;
import com.example.product.model.request.ProductPageRequest;
import com.example.product.model.response.ProductResponse;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/products")
@RequiredArgsConstructor
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Add Product")
    public EntityResponse<UUID, Void> addProduct(@RequestBody @Valid AddOrEditProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Edit Product")
    public EntityResponse<UUID, Void> editProduct(@RequestBody @Valid AddOrEditProductRequest productRequest,
                                                  @PathVariable UUID productId) {
        return productService.editProduct(productRequest, productId);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get Product")
    public EntityResponse<ProductResponse, Void> getProduct(@PathVariable UUID productId) {
        return productService.findProductById(productId);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Deleted Product")
    public EntityResponse<UUID, Void> deleteProduct(@PathVariable UUID productId) {
        return productService.deleteProductById(productId);
    }

    @PostMapping("/all")
    @Operation(summary = "Get All Products")
    public EntityResponse<List<ProductResponse>, PageResponseModel> getAllProducts(@RequestBody ProductPageRequest productPageRequest) {
        return productService.findAllProducts(productPageRequest);
    }

}
