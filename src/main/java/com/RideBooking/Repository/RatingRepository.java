package com.RideBooking.Repository;

import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.Rating;
import com.RideBooking.Entity.Ride;
import com.RideBooking.Entity.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByRide(Ride ride);
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);
}
