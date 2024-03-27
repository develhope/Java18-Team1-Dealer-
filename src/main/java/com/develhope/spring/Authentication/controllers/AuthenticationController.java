package com.develhope.spring.Authentication.controllers;

import com.develhope.spring.Authentication.AuthenticationService;
import com.develhope.spring.Authentication.dto.request.RefreshTokenRequest;
import com.develhope.spring.Authentication.dto.request.SignUpRequest;
import com.develhope.spring.Authentication.dto.request.SigninRequest;
import com.develhope.spring.Authentication.dto.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}