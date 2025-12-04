package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.AuthResponse;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/access-token")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

            String accessToken = jwtUtil.generateToken(loginDTO.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(loginDTO.getUsername());
            Long expirationTime = jwtUtil.getExpirationTime();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);
            authResponse.setExpirationTime(expirationTime);
            return ResponseEntity.ok(authResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Invalid Credential");
        }

    }
}
