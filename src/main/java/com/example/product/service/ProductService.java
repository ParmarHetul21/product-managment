package com.example.product.service;

import com.example.product.common.EntityResponse;
import com.example.product.model.request.AddOrEditProductRequest;
import com.example.product.model.request.PageResponseModel;
import com.example.product.model.request.ProductPageRequest;
import com.example.product.model.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    EntityResponse<UUID, Void> saveProduct(AddOrEditProductRequest productRequest);

    EntityResponse<UUID, Void> editProduct(AddOrEditProductRequest productRequest, UUID productId);

    EntityResponse<ProductResponse, Void> findProductById(UUID productId);

    EntityResponse<UUID, Void> deleteProductById(UUID productId);

    EntityResponse<List<ProductResponse>, PageResponseModel> findAllProducts(ProductPageRequest productPageRequest);
}
