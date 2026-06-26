package com.RideBooking.Controller;

import com.RideBooking.DTO.*;
import com.RideBooking.Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    // ─── POST /auth/signup ───────────────────────────────────────────────────

    @Test
    void signUp_shouldReturn201WithUserDTO() throws Exception {
        SignupDTO signupDto = new SignupDTO();
        signupDto.setEmail("rider@test.com");
        signupDto.setPassword("secret");

        // UserDTO has: name, email, phoneNumber, roles (no id field)
        UserDTO userDto = new UserDTO();
        userDto.setEmail("rider@test.com");
        userDto.setName("Test Rider");

        when(authService.signup(any(SignupDTO.class))).thenReturn(userDto);

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("rider@test.com"))
                .andExpect(jsonPath("$.name").value("Test Rider"));
    }

    // ─── POST /auth/onBoardNewDriver/{userId} ────────────────────────────────

    @Test
    void onBoardNewDriver_shouldReturn201WithDriverDTO() throws Exception {
        OnboardDriverDTO onboardDto = new OnboardDriverDTO();
        onboardDto.setVehicleId("VH-999");

        // DriverDTO has: id, user, rating, available, vehicleId
        DriverDTO driverDto = new DriverDTO();
        driverDto.setId(42L);
        driverDto.setVehicleId("VH-999");

        when(authService.onboardNewDriver(eq(1L), eq("VH-999"))).thenReturn(driverDto);

        mockMvc.perform(post("/auth/onBoardNewDriver/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(onboardDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(42))
                .andExpect(jsonPath("$.vehicleId").value("VH-999"));
    }

    // ─── POST /auth/login ────────────────────────────────────────────────────

    @Test
    void login_shouldReturn200WithAccessTokenAndSetCookie() throws Exception {
        LoginRequestDTO loginDto = new LoginRequestDTO();
        loginDto.setEmail("test@test.com");
        loginDto.setPassword("password");

        when(authService.login("test@test.com", "password"))
                .thenReturn(new String[]{"access-token-xyz", "refresh-token-abc"});

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("token"))
                .andExpect(cookie().httpOnly("token", true))
                .andExpect(jsonPath("$.accessToken").value("access-token-xyz"));
    }

    // ─── POST /auth/refresh ──────────────────────────────────────────────────

    @Test
    void refresh_shouldReturn200WithNewAccessToken() throws Exception {
        Cookie refreshCookie = new Cookie("refreshToken", "old-refresh-token");

        when(authService.refreshToken("old-refresh-token")).thenReturn("new-access-token");

        mockMvc.perform(post("/auth/refresh")
                        .cookie(refreshCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("new-access-token"));
    }

    @Test
    void refresh_shouldThrowWhenNoCookiePresent() {
        // request.getCookies() returns null when no cookies sent → NullPointerException
        // wrapped in a ServletException by MockMvc standalone — we assert the exception propagates
        assertThrows(Exception.class, () ->
                mockMvc.perform(post("/auth/refresh"))
        );
    }
}
