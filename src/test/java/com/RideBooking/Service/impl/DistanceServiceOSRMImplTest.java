package com.RideBooking.Service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.web.client.RestClient;

@SpringBootTest
public class DistanceServiceOSRMImplTest {

    @Test
    public void testDistance() {
        OSRMResponseDto responseDto = RestClient.builder()
                .baseUrl("https://router.project-osrm.org/route/v1/driving/")
                .build()
                .get()
                .uri("77.5946,12.9716;77.5946,12.9716")
                .header("Accept-Encoding", "identity")
                .retrieve()
                .body(OSRMResponseDto.class);
        System.out.println("Distance: " + responseDto.getRoutes().get(0).getDistance());
    }
}
