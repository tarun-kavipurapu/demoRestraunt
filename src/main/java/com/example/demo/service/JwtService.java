package com.example.demo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;
import java.util.function.Function;

/*
* generateToken(username)
*
* extractUsername(string Token)
* extractExpirationDate()
* boolean isTokenValid(String token,UserDetails userDetails)
*
*
*
* */



@Service

public class JwtService {

    public static final String SECRET = "e0c2f4e37886a9cb8055475e487ce18549157c0b21688a53efd64312a1a881a3";

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        //check username from userDetails and extarcted username from claims and also check is the token expired or not
        final String username = extractUserName(token) ;
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // Function<R,T> interface returning T type and accepts input of R type
    public  <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims     claims  = extractAllClaims(token);
        return claimsResolver.apply(claims);//method of functional Interface returning T type
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private  String createToken(Map<String,Object>claims,String username){
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private  Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);//hmac is being used validity on both token creation and token extraction
    }

}
