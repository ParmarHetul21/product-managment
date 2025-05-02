package com.example.product.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static com.example.product.common.CommonConstant.DATE_FORMAT;

/**
 * ApiErrorResponse Builder class for the
 * sending custom Error Response.
 * */

@Builder
@Getter
public class ApiErrorResponse {

    private Integer code;
    private Integer httpStatusCode;
    private String path;
    private String method;
    private String message;
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDateTime timestamp = LocalDateTime.now();

    public static ApiErrorResponse setApiErrorResponse(int code, HttpStatus status, String message, String path, String httpMethod) {

        ApiErrorResponseBuilder responseBuilder = ApiErrorResponse.builder()
                .code(code)
                .httpStatusCode(status.value())
                .message(message);

        responseBuilder.path(path);
        responseBuilder.method(httpMethod);

        return responseBuilder.build();
    }

    public static ApiErrorResponse setApiErrorResponse(int code, HttpStatusCode httpStatusCode, String message, WebRequest webRequest) {

        ApiErrorResponseBuilder responseBuilder = ApiErrorResponse.builder()
                .code(code)
                .httpStatusCode(httpStatusCode.value())
                .message(message);

        if (webRequest instanceof ServletWebRequest servletWebRequest) {
            responseBuilder.path(servletWebRequest.getRequest().getServletPath());
            responseBuilder.method(servletWebRequest.getRequest().getMethod());
        }

        return responseBuilder.build();
    }
}