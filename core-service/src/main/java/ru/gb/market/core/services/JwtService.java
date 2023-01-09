package ru.gb.market.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.gb.market.core.security.JwtProperties;

import java.util.*;

@Component
public class JwtService {

    @Autowired
    private JwtProperties properties;

    public String generateJwtToken(UserDetails user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public String getUsername(final String bearerTokenValue) throws ExpiredJwtException{
        return parse(bearerTokenValue).getSubject();
    }

    public List<GrantedAuthority> getAuthority(final String bearerTokenValue) throws ExpiredJwtException{
        List<String> authority = (List<String>) parse(bearerTokenValue).get("authority");
        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .toList();
    }

    private Claims parse(String value) throws ExpiredJwtException{
            return Jwts.parser()
                    .setSigningKey(properties.getSecret())
                    .parseClaimsJws(value)
                    .getBody();
    }
}