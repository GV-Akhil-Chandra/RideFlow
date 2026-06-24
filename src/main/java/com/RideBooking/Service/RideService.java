package com.RideBooking.Service;


import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.Enums.RideStatus;
import com.RideBooking.Entity.Ride;
import com.RideBooking.Entity.RideRequest;
import com.RideBooking.Entity.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
