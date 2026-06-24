package com.RideBooking.Strategy;

import com.RideBooking.Entity.Enums.PaymentMode;
import com.RideBooking.Strategy.impl.CashPaymentStrategy;
import com.RideBooking.Strategy.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.RideBooking.Entity.Enums.PaymentMode.CASH;
import static com.RideBooking.Entity.Enums.PaymentMode.WALLET;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMode paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
