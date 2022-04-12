package com.example.config.security.jwt;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

// Là lớp 1 instance duy nhất ,  đảm nhận việc tao toke, kiểm tra validate, và lấy ra username từ token.

@Component
public class JwtTokenProvider implements Serializable {
	
	
	//thuoc tinh de doc ghi lai qua trình tuong tác với JWT, looger theo thư viện slf4j
	 private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	
	private static final long jwtExpiration = 86400;
	
	//lay 1 gia tri String trong file property theo key-value;	
	@Value("jwt.secret")
	private String secretKey;

	//Tao ra jwt từ username 
	public String createToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime()+ jwtExpiration  *1000);
		
		return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
		
	}
	
	// Validation Jwt
	 public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	            return true;
	        } catch (SignatureException e) {
	            logger.error("Invalid JWT signature -> Message: {}", e);
	        } catch (MalformedJwtException e) {
	            logger.error("Invalid format Token -> Message: {}", e);
	        } catch (ExpiredJwtException e) {
	            logger.error("Expired JWT token -> Message: {}", e);
	        } catch (UnsupportedJwtException e) {
	            logger.error("Unsupported JWT token -> Message: {}", e);
	        } catch (IllegalArgumentException e) {
	            logger.error("JWT claims string is empty --> Message {}", e);
	        }
	        return false;
	  }
	
	 
	 //Lấy username từ toke đã có 
	 public String getUserFromToke(String token) {
		 String userName1 = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		 System.out.println(userName1);
		 String userName = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
		 System.out.println(userName);
		 return userName;
		 
	 }
	
}
