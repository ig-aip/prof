package org.example.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.Enums.RoleType;
import org.example.db.Workers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final Long accesExpireMs;
    private final Long refreshExpireMs;


    public JwtService(@Value("${jwt.secret}") String secretKey,
                      @Value("${jwt.access-expiration-ms") Long accesExpireMs,
                      @Value("${jwt.refresh-expiration-ms") Long refreshExpireMs) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accesExpireMs = accesExpireMs;
        this.refreshExpireMs = refreshExpireMs;
    }


    public String generateAccessToken(Workers worker){
        return Jwts.builder().setSubject(String.valueOf(worker.getId()))
                .claim("role", worker.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accesExpireMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(){
        return UUID.randomUUID().toString();
    }

    public Jws<Claims> parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
