package com.bank.resona.perdania.backofficebe.config;

import com.bank.resona.perdania.backofficebe.entity.Accounts;
import com.bank.resona.perdania.backofficebe.exception.InternalServerErrorException;
import com.bank.resona.perdania.backofficebe.exception.UnAuthorizeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtHelper {

    @Value("${jwt.expired.inms}")
    private Integer jwtExpiredInMs;

    @Value("${jwt.expired.key}")
    private String secretKey;

    private final ObjectMapper objectMapper;

    public String generateToken(Accounts accounts){
        log.info("generate token from accounts = {}",accounts);
        try{
            String token = Jwts.builder()
                    .setSubject(objectMapper.writeValueAsString(accounts))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(( new Date()).getTime() + jwtExpiredInMs))
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
            return token;
        }catch (Exception e){
            log.error("error generate token = ",e.fillInStackTrace());
            throw new RuntimeException(String.format("failed generate token from accounts %s ",accounts.getUsername()));
        }
    }

    public Accounts getAccountsFromToken(String token){
        try{
            log.info("parse jwt token = {} ",token);
            validateJwtToken(token);
            if (validateJwtToken(token)){
                String tokenInformation = Jwts.parser().setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                log.info("token information = {}",objectMapper.readValue(tokenInformation, Accounts.class));
                return objectMapper.readValue(tokenInformation, Accounts.class);
            }
            throw new UnAuthorizeException("Invalid token !", HttpStatus.UNAUTHORIZED.value());
        }catch (Exception e){
            log.error("error get accounts information from token = ",e.fillInStackTrace());
            throw new InternalServerErrorException(String.format("failed get accounts information from token %s ",token),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private boolean validateJwtToken(String token){
        log.info("validate jwt token={}",token);
        try{
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token);
            log.info("finish validate jwt token");
            return true;
        }catch (SignatureException e){
            log.error("error because sinature={}",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("error because malformed={}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("error because expired={}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("error because unsupported={}",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("error because illegal argument={}",e.getMessage());
        }
        return false;
    }

}
