package com.example.product.exception.handler;

import com.example.product.common.ApiErrorResponse;
import com.example.product.common.CommonErrorConstant;
import com.example.product.exception.ProductServiceException;
import com.example.product.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Translator translator;

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ApiErrorResponse> handleRuntimeException(final RuntimeException ex, final WebRequest request) {

        final ApiErrorResponse apiErrorResponse = ApiErrorResponse.setApiErrorResponse(
                CommonErrorConstant.SOMETHING_WENT_WRONG.getErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                translator.toLocal(CommonErrorConstant.SOMETHING_WENT_WRONG.getErrorMessage()), request);

        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }

    @ExceptionHandler(value = ProductServiceException.class)
    protected ResponseEntity<ApiErrorResponse> handleProductServiceException(final ProductServiceException exception, final WebRequest request) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ApiErrorResponse.setApiErrorResponse(
                        exception.getErrorCode(), exception.getHttpStatus(),
                        exception.getMessage(), request));
    }
}
