package com.example.product.model.request;

import com.example.product.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static com.example.product.common.CommonConstant.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageRequest {

    @Builder.Default
    private String sortField = Product.Fields.createdAt;
    @Builder.Default
    private String sortType = DESC;
    @Builder.Default
    private Integer pageNumber = DEFAULT_PAGE_NUMBER;
    @Builder.Default
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    @JsonIgnore
    private PageRequest pageRequest;


    public PageRequest getPageRequest() {
        return PageRequest.of(this.pageNumber, this.pageSize)
                .withSort(Sort.by(Sort.Direction.fromString(this.getSortType()), this.getSortField()));
    }

}
