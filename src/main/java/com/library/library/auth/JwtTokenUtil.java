package com.library.library.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.library.entities.User;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;


public class JwtTokenUtil implements Serializable {

    private long EXPIRE_IN_SECONDS = 3600;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${app.domain}")
    private String domain;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);

        long now = new Date().getTime();
        long expireTime = now + (EXPIRE_IN_SECONDS * 1000);
        Date expireDate = new Date(expireTime);

        return JWT.create()
           .withIssuer(domain)
           .withIssuedAt(new Date())
           .withClaim("username", user.getUsername())
           .withExpiresAt(expireDate)
           .sign(algorithm);
    }

    public boolean validate(String token){
        try{
            JWTVerifier jwtVerifier = JWT.require( Algorithm.HMAC512(token) )
                .withIssuer(domain)
                .acceptExpiresAt(EXPIRE_IN_SECONDS)
                .build();
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    public String getClaimFromToken(String token, String claimKey) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get(claimKey).toString();
    }

    public String getUsername(String token){
        return getClaimFromToken(token, "username");
    }


}
