package com.example.product.exception.handler;

import com.example.product.common.ApiErrorResponse;
import com.example.product.common.CommonErrorConstant;
import com.example.product.common.Error;
import com.example.product.common.ErrorType;
import com.example.product.exception.ProductServiceException;
import com.example.product.utility.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Order(value = HIGHEST_PRECEDENCE)
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        final Stream<Error.ErrorDetail>
                fieldErrorStream = getStreamOfErrorDetail(ex.getBindingResult().getFieldErrors(), ErrorType.INVALID_FIELD);
        final Stream<Error.ErrorDetail> globalErrorStream =
                getStreamOfErrorDetail(ex.getBindingResult().getGlobalErrors(), ErrorType.INVALID_DATA);
        return ResponseEntity.badRequest()
                .body(getApiErrorResponse(Stream.concat(fieldErrorStream, globalErrorStream).toList(), request));
    }

    private Stream<Error.ErrorDetail> getStreamOfErrorDetail(
            final List<? extends ObjectError> objectErrors,
            final ErrorType errorType) {
        return objectErrors.stream().map(objectError -> {
            if (objectError instanceof FieldError fieldError) {
                return getFieldErrorDetail(fieldError, errorType);
            } else {
                return getObjectErrorDetail(objectError, errorType);
            }
        });
    }

    private Error.ErrorDetail getObjectErrorDetail(ObjectError objectError,
                                                   ErrorType errorType) {
        return Error.ErrorDetail.builder().message(objectError.getDefaultMessage())
                .type(errorType).build();
    }

    private Error.ErrorDetail getFieldErrorDetail(FieldError fieldError, ErrorType errorType) {
        return Error.ErrorDetail.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).type(errorType).build();
    }

    private ApiErrorResponse getApiErrorResponse(final List<Error.ErrorDetail> errorDetails, WebRequest webRequest) {

        String errorMessage = errorDetails.stream()
                .map(errorDetail -> errorDetail.getField() + " : " + errorDetail.getMessage())
                .collect(Collectors.joining(", "));
        return ApiErrorResponse.buildAPIErrorResponse(webRequest, HttpStatus.BAD_REQUEST, -1, errorMessage, errorDetails);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(final AuthenticationException exception,
                                                                   final WebRequest request) {

        logger.error("AuthenticationException: ", exception);
        final ApiErrorResponse errorResponse = ApiErrorResponse.setApiErrorResponse(
                CommonErrorConstant.ACCESS_DENIED.getErrorCode(),
                HttpStatus.UNAUTHORIZED,
                translator.toLocal(CommonErrorConstant.ACCESS_DENIED.getErrorMessage()),
                request
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
