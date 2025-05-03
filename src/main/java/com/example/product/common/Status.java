package com.example.product.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    DELETED(0),
    ACTIVE(1),;

    public final int statusValue;
}
