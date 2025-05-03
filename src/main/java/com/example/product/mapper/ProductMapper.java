package com.example.product.mapper;

import com.example.product.model.entity.Product;
import com.example.product.model.request.AddOrEditProductRequest;
import com.example.product.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {


    Product productRequestToProduct(AddOrEditProductRequest productRequest);

    void productRequestToProduct(@MappingTarget Product.ProductBuilder builder,
                                 AddOrEditProductRequest productRequest);

    ProductResponse mapProductToProductResponse(Product product);

    List<ProductResponse> mapProductListToProductResponseList(List<Product> products);
}
