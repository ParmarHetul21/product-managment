package com.example.product.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorConstant {

    SOMETHING_WENT_WRONG(-1, "something.went.wrong"),
    PRODUCT_NAME_EXIST(-10000, "product.name.exist"),
    PRODUCT_NOT_FOUND(-10001, "product.not.found"),
    ACCESS_DENIED(-10002, "access.denied"),
    USER_NOT_FOUND(-10003, "user.email.not.found"),
    INVALID_CREDENTIALS(-10004, "invalid.credentials"),;

    private final int errorCode;
    private final String errorMessage;
}
