package com.RideBooking.Service;


import com.RideBooking.DTO.DriverDTO;
import com.RideBooking.DTO.RiderDTO;
import com.RideBooking.Entity.Ride;

public interface RatingService {

    DriverDTO rateDriver(Ride ride, Integer rating);
    RiderDTO rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
