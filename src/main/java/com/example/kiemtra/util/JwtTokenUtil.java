package com.example.kiemtra.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JwtTokenUtil {

    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${jwt.time_expiration}")
    private Integer TIME_EXPIRATION;

    //get Username from jwt
    public String extractUsername(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    //check user token expired
    public Date extractExpriration(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    }

    //is token expired
    public Boolean isTokenExpired(String token)
    {
        return extractExpriration(token).before(new Date());
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //generate token
    public String generateToken(UserDetails userDetails)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, TIME_EXPIRATION);
        return Jwts.builder().setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(calendar.getTime())
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
    }
}
