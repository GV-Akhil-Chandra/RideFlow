package com.RideBooking.Controller;

import com.RideBooking.DTO.*;
import com.RideBooking.Service.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DriverControllerTest {

    @Mock
    private DriverService driverService;

    @InjectMocks
    private DriverController driverController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    // ─── POST /drivers/acceptRide/{rideRequestId} ────────────────────────────

    @Test
    void acceptRide_shouldReturn200WithRideDTO() throws Exception {
        // RideDTO has no Lombok — assert on status; actual ride data comes from service layer
        RideDTO rideDTO = new RideDTO();
        when(driverService.acceptRide(2L)).thenReturn(rideDTO);

        mockMvc.perform(post("/drivers/acceptRide/2"))
                .andExpect(status().isOk());
    }

    // ─── POST /drivers/startRide/{rideRequestId} ─────────────────────────────

    @Test
    void startRide_shouldReturn200WithRideDTO() throws Exception {
        // RideStartDTO has: otp
        RideStartDTO rideStartDTO = new RideStartDTO();
        rideStartDTO.setOtp("4321");

        RideDTO rideDTO = new RideDTO();
        when(driverService.startRide(2L, "4321")).thenReturn(rideDTO);

        mockMvc.perform(post("/drivers/startRide/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rideStartDTO)))
                .andExpect(status().isOk());
    }

    // ─── POST /drivers/endRide/{rideId} ──────────────────────────────────────

    @Test
    void endRide_shouldReturn200WithCompletedRideDTO() throws Exception {
        RideDTO rideDTO = new RideDTO();
        when(driverService.endRide(5L)).thenReturn(rideDTO);

        mockMvc.perform(post("/drivers/endRide/5"))
                .andExpect(status().isOk());
    }

    // ─── POST /drivers/cancelRide/{rideId} ───────────────────────────────────

    @Test
    void cancelRide_shouldReturn200WithCancelledRideDTO() throws Exception {
        RideDTO rideDTO = new RideDTO();
        when(driverService.cancelRide(5L)).thenReturn(rideDTO);

        mockMvc.perform(post("/drivers/cancelRide/5"))
                .andExpect(status().isOk());
    }

    // ─── POST /drivers/rateRider ──────────────────────────────────────────────

    @Test
    void rateRider_shouldReturn200WithUpdatedRiderDTO() throws Exception {
        // RatingDTO has: rideId, rating
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRideId(1L);
        ratingDTO.setRating(4);

        // RiderDTO has: id, userDTO, rating
        RiderDTO riderDTO = new RiderDTO();
        riderDTO.setId(9L);
        riderDTO.setRating(4.2);

        when(driverService.rateRider(1L, 4)).thenReturn(riderDTO);

        mockMvc.perform(post("/drivers/rateRider")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9))
                .andExpect(jsonPath("$.rating").value(4.2));
    }

    // ─── GET /drivers/getMyProfile ───────────────────────────────────────────

    @Test
    void getMyProfile_shouldReturn200WithDriverDTO() throws Exception {
        // DriverDTO has: id, user, rating, available, vehicleId
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(2L);
        driverDTO.setVehicleId("KA-01-AB-1234");
        driverDTO.setAvailable(true);

        when(driverService.getMyProfile()).thenReturn(driverDTO);

        mockMvc.perform(get("/drivers/getMyProfile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.vehicleId").value("KA-01-AB-1234"))
                .andExpect(jsonPath("$.available").value(true));
    }

    // ─── GET /drivers/getMyRides ─────────────────────────────────────────────

    @Test
    void getAllMyRides_shouldReturn200WithPageOfRideDTOs() throws Exception {
        RideDTO rideDTO = new RideDTO();
        Page<RideDTO> page = new PageImpl<>(List.of(rideDTO), PageRequest.of(0, 10), 1);

        when(driverService.getAllMyRides(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/drivers/getMyRides")
                        .param("pageOffset", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void getAllMyRides_withDefaultParams_shouldReturn200WithEmptyPage() throws Exception {
        Page<RideDTO> page = new PageImpl<>(List.of(), PageRequest.of(0, 10), 0);
        when(driverService.getAllMyRides(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/drivers/getMyRides"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(0));
    }
}
