package com.RideBooking.Strategy;

import com.RideBooking.Entity.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);

}
