package com.RideBooking.Advice;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String packageName = returnType.getContainingClass().getPackageName();
        // Exclude Swagger/OpenAPI
        if(packageName.startsWith("org.springdoc")) {
            return false;
        }

        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ApiResponse<?>){
            return body;
        }

        // passing direct body in case of OpenAPI swagger as Global response handler wrapper breaks swagger
        String path = request.getURI().getPath();
        if (path.contains("/v3/api-docs") || path.contains("/swagger-ui")) {
            return body;
        }

        if (path.contains("/auth/")) {
            return body;
        }

        return new ApiResponse<>(body);
    }
}
