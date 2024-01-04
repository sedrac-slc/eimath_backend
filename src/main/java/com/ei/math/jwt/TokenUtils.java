package com.ei.math.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenUtils {

    private static final String TOKEN_SECRET = "480924bd-b5d1-4a7f-8398-b2dd92e79839";
    private static final long TOKEN_SEGUNDS = 2_592_000L;

    public static String createToken(String username, String email) {
        long experedTime = TOKEN_SEGUNDS * 1_000;

        Date expiredAt = new Date(System.currentTimeMillis() + experedTime);

        Map<String, Object> extra = new HashMap<>();

        extra.put("email", email);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiredAt)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }

}
