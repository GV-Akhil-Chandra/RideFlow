package com.RideBooking.Strategy;

import com.RideBooking.Entity.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);

}
