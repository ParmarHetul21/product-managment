package com.example.product.service.impl;

import com.example.product.common.EntityResponse;
import com.example.product.exception.ProductServiceException;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.entity.Product;
import com.example.product.model.request.AddOrEditProductRequest;
import com.example.product.model.request.PageResponseModel;
import com.example.product.model.request.ProductPageRequest;
import com.example.product.model.response.ProductResponse;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import com.example.product.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.product.common.CommonErrorConstant.PRODUCT_NAME_EXIST;
import static com.example.product.common.CommonErrorConstant.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Translator translator;
    private final ProductMapper productMapper;

    @Override
    public EntityResponse<UUID, Void> saveProduct(AddOrEditProductRequest productRequest) {

        validateProductName(productRequest.getName());
        Product product = productMapper.productRequestToProduct(productRequest);
        product = productRepository.save(product);
        return EntityResponse.setEntityResponse(HttpStatus.CREATED.value(),
                translator.toLocal("product.created"), product.getId());
    }

    @Override
    public EntityResponse<UUID, Void> editProduct(AddOrEditProductRequest productRequest,
                                                  UUID productId) {

        Product.ProductBuilder builder = validateProductId(productId).toBuilder();
        productMapper.productRequestToProduct(builder, productRequest);
        productRepository.save(builder.build());
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(),
                translator.toLocal("product.updated"),
                productId);
    }

    @Override
    public EntityResponse<ProductResponse, Void> findProductById(UUID productId) {

        Product product = validateProductId(productId);
        ProductResponse productResponse = productMapper.mapProductToProductResponse(product);
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(),
                translator.toLocal("product.fetched"),
                productResponse);
    }

    @Override
    @Transactional
    public EntityResponse<UUID, Void> deleteProductById(UUID productId) {

        productRepository.deleteById(productId);
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(),
                translator.toLocal("product.deleted"),
                productId);
    }

    @Override
    public EntityResponse<List<ProductResponse>, PageResponseModel> findAllProducts(ProductPageRequest productPageRequest) {

        Page<Product> product = productRepository.findAll(productPageRequest.getPageRequest());
        return EntityResponse.setEntityResponse(HttpStatus.OK.value(),
                translator.toLocal("product.fetched"),
                productMapper.mapProductListToProductResponseList(product.getContent()),
                PageResponseModel.builder()
                        .currentPage(product.getNumber())
                        .pageSize(product.getSize())
                        .totalPages(product.getTotalPages())
                        .totalElements(product.getTotalElements())
                        .build());
    }

    private Product validateProductId(UUID productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(PRODUCT_NOT_FOUND.getErrorCode(),
                        translator.toLocal(PRODUCT_NOT_FOUND.getErrorMessage()), HttpStatus.NOT_FOUND));
    }


    private void validateProductName(String productName) {

        if (Optional.ofNullable(productName).isPresent() &&
                productRepository.existsByNameIgnoreCase(productName)) {
            throw new ProductServiceException(PRODUCT_NAME_EXIST.getErrorCode(),
                    translator.toLocal(PRODUCT_NAME_EXIST.getErrorMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
