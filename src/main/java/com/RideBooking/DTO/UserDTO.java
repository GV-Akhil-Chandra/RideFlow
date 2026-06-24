package com.RideBooking.DTO;

import com.RideBooking.Entity.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;

    private String email;

    private Long phoneNumber;

    private Set<Role> roles;
}
