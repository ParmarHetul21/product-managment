package com.example.product.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonConstant {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DELETED_WHERE_CLAUSE = "status <> 'DELETED'";
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 1000;
}
