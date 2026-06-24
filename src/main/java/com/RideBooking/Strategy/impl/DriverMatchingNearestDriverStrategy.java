package com.RideBooking.Strategy.impl;


import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.RideRequest;
import com.RideBooking.Repository.DriverRepository;
import com.RideBooking.Strategy.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
