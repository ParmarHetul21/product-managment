package com.example.product.service;

import com.example.product.common.EntityResponse;
import com.example.product.exception.ProductServiceException;
import com.example.product.model.entity.Product;
import com.example.product.model.request.AddOrEditProductRequest;
import com.example.product.model.response.ProductResponse;
import com.example.product.utility.RequestResponseUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static com.example.product.common.CommonErrorConstant.PRODUCT_NAME_EXIST;
import static com.example.product.common.CommonErrorConstant.PRODUCT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest extends BaseServiceTest {

    @Test
    @DisplayName(value = "test for create the product")
    void testCreateProduct() {
        createProduct(RequestResponseUtility.createAddOrEditProductRequest());
    }

    @Test
    @DisplayName(value = "test for edit the product")
    void testEditProduct() {

        Product addedProduct = createProduct(RequestResponseUtility.createAddOrEditProductRequest());

        AddOrEditProductRequest addOrEditProductRequest = RequestResponseUtility.createAddOrEditProductRequest();
        EntityResponse<UUID, Void> editEntityResponse
                = productService.editProduct(addOrEditProductRequest, addedProduct.getId());
        assertThat(editEntityResponse.getData()).isEqualTo(addedProduct.getId());
        assertThat(editEntityResponse.getCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(editEntityResponse.getMessage()).isEqualTo(translator.toLocal("product.updated"));
        Optional<Product> product = productRepository.findById(editEntityResponse.getData());
        assertThat(product).isPresent();
        assertProductEntityWithRequest(product.get(), addOrEditProductRequest);
    }


    @Test
    @DisplayName(value = "Get Product By Id")
    void testGetProductById() {

        Product addedProduct = createProduct(RequestResponseUtility.createAddOrEditProductRequest());
        EntityResponse<ProductResponse, Void> fetchedProduct = productService.findProductById(addedProduct.getId());
        assertThat(fetchedProduct).isNotNull();
        assertThat(fetchedProduct.getCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(fetchedProduct.getMessage()).isEqualTo(translator.toLocal("product.fetched"));
        assertThat(fetchedProduct.getData()).isNotNull();
    }

    @Test
    @DisplayName(value = "Deleted Product By id")
    void testDeleteProductById() {

        Product addedProduct = createProduct(RequestResponseUtility.createAddOrEditProductRequest());
        EntityResponse<UUID, Void> entityResponse = productService.deleteProductById(addedProduct.getId());
        assertThat(entityResponse).isNotNull();
        assertThat(entityResponse.getCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(entityResponse.getMessage()).isEqualTo(translator.toLocal("product.deleted"));
        assertThat(entityResponse.getData()).isNotNull();
        assertThat(productRepository.findById(addedProduct.getId())).isEmpty();
    }


    @Test
    @DisplayName(value = "Test Duplicate Product Name")
    void testCreateDuplicateProduct() {

        AddOrEditProductRequest addOrEditProductRequest
                = RequestResponseUtility.createAddOrEditProductRequest();
        createProduct(addOrEditProductRequest);
        ProductServiceException productServiceException
                = assertThrows(ProductServiceException.class, () -> productService.saveProduct(addOrEditProductRequest));
        assertThat(productServiceException).isNotNull();
        assertThat(productServiceException.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(productServiceException.getMessage()).isEqualTo(translator.toLocal(PRODUCT_NAME_EXIST.getErrorMessage()));
        assertThat(productServiceException.getErrorCode()).isEqualTo(PRODUCT_NAME_EXIST.getErrorCode());
    }

    @Test
    @DisplayName(value = "Test Product Not Found")
    void testProductNotFound() {

        ProductServiceException productServiceException = assertThrows(ProductServiceException.class, () -> productService.findProductById(UUID.randomUUID()));
        assertThat(productServiceException).isNotNull();
        assertThat(productServiceException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(productServiceException.getMessage()).isEqualTo(translator.toLocal(PRODUCT_NOT_FOUND.getErrorMessage()));
        assertThat(productServiceException.getErrorCode()).isEqualTo(PRODUCT_NOT_FOUND.getErrorCode());
    }

    private void assertProductEntityWithRequest(Product product, AddOrEditProductRequest addOrEditProductRequest) {

        assertThat(product.getName()).isEqualTo(addOrEditProductRequest.getName());
        assertThat(product.getDescription()).isEqualTo(addOrEditProductRequest.getDescription());
        assertThat(product.getPrice()).isEqualTo(addOrEditProductRequest.getPrice());
        assertThat(product.getQuantity()).isEqualTo(addOrEditProductRequest.getQuantity());
        assertThat(product.getId()).isNotNull();
    }

    private Product createProduct(AddOrEditProductRequest productRequest) {
        EntityResponse<UUID, Void> entityResponse
                = productService.saveProduct(productRequest);
        assertThat(entityResponse.getData()).isNotNull();
        assertThat(entityResponse.getCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(entityResponse.getMessage()).isEqualTo(translator.toLocal("product.created"));
        assertThat(entityResponse.getData()).isNotNull();
        assertThat(entityResponse.getMetaData()).isNull();
        Optional<Product> product = productRepository.findById(entityResponse.getData());
        assertThat(product).isPresent();
        return product.get();
    }
}
