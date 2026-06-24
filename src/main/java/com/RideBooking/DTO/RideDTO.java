package com.RideBooking.DTO;

import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.Enums.PaymentMode;
import com.RideBooking.Entity.Enums.RideStatus;
import com.RideBooking.Entity.Rider;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {
    private Long id;
    private PointDTO pickupLocation;
    private PointDTO dropOffLocation;

    private LocalDateTime createdTime;
    private RiderDTO rider;
    private DriverDTO driver;
    private PaymentMode paymentMode;

    private RideStatus rideStatus;

    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
