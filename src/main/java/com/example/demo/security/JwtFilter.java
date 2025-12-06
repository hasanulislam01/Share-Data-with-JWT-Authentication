//package com.example.demo.security;
//
//import com.example.demo.dto.LoginDTO;
//import com.example.demo.model.AuthResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtFilter extends UsernamePasswordAuthenticationFilter {
//    private final JwtUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//
//    public JwtFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
//        this.jwtUtil = jwtUtil;
//        this.authenticationManager = authenticationManager;
////        setFilterProcessesUrl("/api/auth/access-token");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
//
//        String accessToken = jwtUtil.generateToken(username);
//        String refreshToken = jwtUtil.generateRefreshToken(username);
//
//        AuthResponse authResponse = new AuthResponse(accessToken, refreshToken, jwtUtil.getExpirationTime());
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        response.getWriter().write(objectMapper.writeValueAsString(authResponse));
////        super.successfulAuthentication(request, response, chain, authResult);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        Map<String, Object> errorMap = new HashMap<>();
//        errorMap.put("error", "Invalid Username or Password");
//        errorMap.put("message", failed.getMessage());
//        errorMap.put("timeStamp", LocalDateTime.now().toString());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        response.getWriter().write(objectMapper.writeValueAsString(errorMap));
////        super.unsuccessfulAuthentication(request, response, failed);
//    }
//}
