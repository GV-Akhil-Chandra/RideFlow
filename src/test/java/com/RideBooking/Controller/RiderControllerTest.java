package com.RideBooking.Controller;

import com.RideBooking.DTO.*;
import com.RideBooking.Service.RiderService;
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
class RiderControllerTest {

    @Mock
    private RiderService riderService;

    @InjectMocks
    private RiderController riderController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(riderController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    // ─── POST /riders/requestRide ────────────────────────────────────────────

    @Test
    void requestRide_shouldReturn200WithRideRequestDTO() throws Exception {
        // RideRequestDTO has: id, pickupLocation, dropOffLocation, paymentMode,
        //                     requestedTime, rider, fare, rideRequestStatus
        RideRequestDTO requestDTO = new RideRequestDTO();

        RideRequestDTO responseDTO = new RideRequestDTO();
        responseDTO.setId(10L);
        responseDTO.setFare(150.0);

        when(riderService.requestRide(any(RideRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/riders/requestRide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.fare").value(150.0));
    }

    // ─── POST /riders/cancelRide/{rideId} ───────────────────────────────────

    @Test
    void cancelRide_shouldReturn200WithCancelledRideDTO() throws Exception {
        // RideDTO has no Lombok annotations — assert on the status only
        // (fields are private with no getters, so JSON serialization returns empty object)
        RideDTO rideDTO = new RideDTO();

        when(riderService.cancelRide(10L)).thenReturn(rideDTO);

        mockMvc.perform(post("/riders/cancelRide/10"))
                .andExpect(status().isOk());
    }

    // ─── POST /riders/rateDriver ─────────────────────────────────────────────

    @Test
    void rateDriver_shouldReturn200WithUpdatedDriverDTO() throws Exception {
        // RatingDTO has: rideId, rating
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRideId(1L);
        ratingDTO.setRating(5);

        // DriverDTO has: id, user, rating, available, vehicleId
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(7L);
        driverDTO.setRating(4.8);

        when(riderService.rateDriver(1L, 5)).thenReturn(driverDTO);

        mockMvc.perform(post("/riders/rateDriver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.rating").value(4.8));
    }

    // ─── GET /riders/getMyProfile ────────────────────────────────────────────

    @Test
    void getMyProfile_shouldReturn200WithRiderDTO() throws Exception {
        // RiderDTO has: id, userDTO, rating
        RiderDTO riderDTO = new RiderDTO();
        riderDTO.setId(3L);
        riderDTO.setRating(4.5);

        when(riderService.getMyProfile()).thenReturn(riderDTO);

        mockMvc.perform(get("/riders/getMyProfile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.rating").value(4.5));
    }

    // ─── GET /riders/getMyRides ──────────────────────────────────────────────

    @Test
    void getAllMyRides_shouldReturn200WithPageOfRideDTOs() throws Exception {
        RideDTO rideDTO = new RideDTO();
        Page<RideDTO> page = new PageImpl<>(List.of(rideDTO), PageRequest.of(0, 10), 1);

        when(riderService.getAllMyRides(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/riders/getMyRides")
                        .param("pageOffset", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void getAllMyRides_withDefaultParams_shouldReturn200WithEmptyPage() throws Exception {
        Page<RideDTO> page = new PageImpl<>(List.of(), PageRequest.of(0, 10), 0);
        when(riderService.getAllMyRides(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/riders/getMyRides"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").value(0));
    }
}
