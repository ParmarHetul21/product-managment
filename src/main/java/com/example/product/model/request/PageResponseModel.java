package com.example.product.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageResponseModel {

    private int pageSize;
    private int currentPage;
    private long totalElements;
    private long totalPages;
}
