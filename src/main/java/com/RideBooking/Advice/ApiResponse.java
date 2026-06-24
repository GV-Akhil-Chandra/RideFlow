package com.RideBooking.Advice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse <T>{
    private LocalDateTime timestamp;
    private T body;
    private ApiError error;

    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(T body){
        this();
        this.body = body;
    }

    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }
}
