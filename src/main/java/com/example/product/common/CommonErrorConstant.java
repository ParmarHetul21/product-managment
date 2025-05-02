package com.example.product.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorConstant {

    SOMETHING_WENT_WRONG(-1, "something.went.wrong"),;

    private final int errorCode;
    private final String errorMessage;
}
