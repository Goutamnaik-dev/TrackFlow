package com.trackflow.trackflow.controller;

import com.trackflow.trackflow.dto.LoginRequest;
import com.trackflow.trackflow.dto.RegisterRequest;
import com.trackflow.trackflow.model.AppUser;
import com.trackflow.trackflow.service.AppUserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;

    public AuthController(
            AppUserService appUserService,
            AuthenticationManager authenticationManager) {

        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail().trim().toLowerCase(),
                                    request.getPassword()
                            )
                    );

            SecurityContext context =
                    SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            new HttpSessionSecurityContextRepository()
                    .saveContext(context, httpRequest, httpResponse);

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "email", authentication.getName()
            ));

        } catch (BadCredentialsException exception) {

            SecurityContextHolder.clearContext();

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message", "Invalid email or password"
                    ));
        }
    }

    @GetMapping("/csrf")
    public Map<String, String> getCsrfToken(CsrfToken csrfToken) {

        return Map.of(
                "token",
                csrfToken.getToken()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request) {

        try {
            AppUser user = appUserService.registerUser(
                    request.getEmail(),
                    request.getPassword()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Registration successful!",
                            "email", user.getEmail()
                    ));

        } catch (IllegalArgumentException exception) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", exception.getMessage()
                    ));
        }
    }
}