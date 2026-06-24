package com.RideBooking.Advice;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ApiError {
    private String message;
    private HttpStatus statusCode;
    private List<String> errors;
}
