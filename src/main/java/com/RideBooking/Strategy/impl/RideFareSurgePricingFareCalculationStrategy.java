package com.RideBooking.Strategy.impl;


import com.RideBooking.Entity.RideRequest;
import com.RideBooking.Service.DistanceService;
import com.RideBooking.Strategy.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
