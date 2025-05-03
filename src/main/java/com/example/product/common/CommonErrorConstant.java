package com.example.product.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorConstant {

    SOMETHING_WENT_WRONG(-1, "something.went.wrong"),
    PRODUCT_NAME_EXIST(-10000, "product.name.exist"),
    PRODUCT_NOT_FOUND(-10001, "product.not.found"),;

    private final int errorCode;
    private final String errorMessage;
}
