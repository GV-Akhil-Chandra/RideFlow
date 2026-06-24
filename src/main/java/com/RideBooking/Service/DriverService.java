package com.RideBooking.Service;

import com.RideBooking.DTO.DriverDTO;
import com.RideBooking.DTO.RideDTO;
import com.RideBooking.DTO.RiderDTO;
import com.RideBooking.Entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideRequestId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId, String otp);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
