package com.RideBooking.Service;

import com.RideBooking.Entity.Enums.TransactionMethod;
import com.RideBooking.Entity.Ride;
import com.RideBooking.Entity.User;
import com.RideBooking.Entity.Wallet;


public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount,
                            String transactionId, Ride ride,
                            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount,
                                 String transactionId, Ride ride,
                                 TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
