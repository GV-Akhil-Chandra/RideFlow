package com.RideBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {
    private Long id;
    private UserDTO userDTO;
    private Double rating;
}
