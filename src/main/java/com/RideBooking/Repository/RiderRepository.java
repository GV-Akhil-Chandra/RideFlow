package com.RideBooking.Repository;

import com.RideBooking.Entity.Rider;
import com.RideBooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}