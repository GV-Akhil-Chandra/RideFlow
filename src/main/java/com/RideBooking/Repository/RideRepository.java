package com.RideBooking.Repository;

import com.RideBooking.Entity.Driver;
import com.RideBooking.Entity.Ride;
import com.RideBooking.Entity.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}