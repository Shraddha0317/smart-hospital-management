package com.shradha.smart_hospital_management.auth.service;


import com.shradha.smart_hospital_management.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;




    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user){

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) (getSigningKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



    public String extractUserName(String  token){

       return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return  extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token , User user){
        final String Username = extractUserName(token);

        return Username.equals(user.getEmail())&& !isTokenExpired(token);
    }
}
