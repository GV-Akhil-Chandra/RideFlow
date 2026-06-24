package com.RideBooking.Service.impl;


import com.RideBooking.Entity.Enums.PaymentStatus;
import com.RideBooking.Entity.Payment;
import com.RideBooking.Entity.Ride;
import com.RideBooking.Exception.ResourceNotFoundException;
import com.RideBooking.Repository.PaymentRepository;
import com.RideBooking.Service.PaymentService;
import com.RideBooking.Strategy.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: "+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMode()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMode(ride.getPaymentMode())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
