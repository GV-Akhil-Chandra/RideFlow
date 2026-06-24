package com.RideBooking.Service;

import com.RideBooking.DTO.DriverDTO;
import com.RideBooking.DTO.SignupDTO;
import com.RideBooking.DTO.UserDTO;

public interface AuthService {

    String[] login(String email, String password);

    UserDTO signup(SignupDTO signupDto);

    DriverDTO onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
