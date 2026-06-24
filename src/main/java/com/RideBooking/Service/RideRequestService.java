package com.RideBooking.Service;


import com.RideBooking.Entity.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
