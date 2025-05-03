package com.example.product.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@SuperBuilder(toBuilder = true)
public class Error {
    private int code;
    private String message;
    private List<ErrorDetail> errorDetails;

    @Data
    @Builder
    @JsonInclude(NON_EMPTY)
    public static class ErrorDetail {
        private ErrorType type;
        private String field;
        private String message;
    }
}
