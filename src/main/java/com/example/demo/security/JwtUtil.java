package com.example.demo.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.access-token-expiration-ms}")
    private Long accessTokenValidityMs;

    @Value("${security.jwt.refresh-token-expiration-ms}")
    private Long refreshTokenValidityMs;

//    private final Key key;
//    private final long accessTokenValidityMs;
//    private final long refreshTokenValidityMs;

//    public JwtUtil(@Value("${security.jwt.secret}") String secret, @Value("${security.jwt.access-token-expiration-ms}") long accessTokenValidityMs, @Value("${security.jwt.refresh-token-expiration-ms}") long refreshTokenValidityMs) {
//        this.key = Keys.hmacShaKeyFor(secret.getBytes());
//        this.accessTokenValidityMs = accessTokenValidityMs;
//        this.refreshTokenValidityMs = refreshTokenValidityMs;
//    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);  // ✅ Generates a proper HS512 key
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (accessTokenValidityMs * 1000)))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (refreshTokenValidityMs * 1000)))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Long getExpirationTime(){
        return accessTokenValidityMs;
    }


}
