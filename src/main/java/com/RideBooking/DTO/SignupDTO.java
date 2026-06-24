package com.RideBooking.DTO;

import com.RideBooking.Entity.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;
}
