package org.example.hickingshop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.hickingshop.configuration.custom.CustomUser;
import org.example.hickingshop.entities.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String tokenKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    public String generateToken(CustomUser user) {

        Map<String, Object> claims = new HashMap<>();
        String role = user.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        claims.put("role", role);

        return Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .signWith(getSignInKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Claims getTokenPayload(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public String getTokenUserIdClaim(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseClaimsJws(token).getBody().get("userId", String.class);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
