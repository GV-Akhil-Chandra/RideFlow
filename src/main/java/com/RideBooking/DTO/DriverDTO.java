package com.RideBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Long id;
    private UserDTO user;
    private Double rating;
    private Boolean available;
    private String vehicleId;
}