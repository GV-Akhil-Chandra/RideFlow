package com.RideBooking.Strategy;

import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
