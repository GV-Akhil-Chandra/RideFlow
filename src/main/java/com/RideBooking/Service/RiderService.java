package com.RideBooking.Service;

import com.RideBooking.DTO.DriverDTO;
import com.RideBooking.DTO.RideDTO;
import com.RideBooking.DTO.RideRequestDTO;
import com.RideBooking.DTO.RiderDTO;
import com.RideBooking.Entity.Rider;
import com.RideBooking.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);

    DriverDTO rateDriver(Long rideId, Integer rating);

    RiderDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
