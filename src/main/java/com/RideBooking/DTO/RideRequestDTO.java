package com.RideBooking.DTO;

import com.RideBooking.Entity.Enums.PaymentMode;
import com.RideBooking.Entity.Enums.RideRequestStatus;
import com.RideBooking.Entity.Rider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RideRequestDTO {
    private Long id;

    private PointDTO pickupLocation;
    private PointDTO dropOffLocation;
    private PaymentMode paymentMode;

    private LocalDateTime requestedTime;

    private RiderDTO rider;
    private Double fare;

    private RideRequestStatus rideRequestStatus;
}
