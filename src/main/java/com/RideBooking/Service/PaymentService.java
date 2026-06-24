package com.RideBooking.Service;


import com.RideBooking.Entity.Enums.PaymentStatus;
import com.RideBooking.Entity.Payment;
import com.RideBooking.Entity.Ride;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
