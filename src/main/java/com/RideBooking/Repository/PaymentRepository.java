package com.RideBooking.Repository;

import com.RideBooking.Entity.Payment;
import com.RideBooking.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
