package com.RideBooking.Advice;

import com.RideBooking.Exception.ResourceNotFoundException;
import com.RideBooking.Exception.RuntimeConflictException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(RuntimeConflictException exception) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.CONFLICT)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.FORBIDDEN)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .errors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    // main builder method to generate response
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatusCode());
    }
}
