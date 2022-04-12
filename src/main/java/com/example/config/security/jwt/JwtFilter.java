package com.example.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.config.security.impl.MyUseDetailsService;
import com.example.config.security.impl.UserDetailsImpl;
import com.example.service.rank1.IAccountService;


// loc du lieu Jwts trươc khi mapping một request.

public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	@Autowired	
	private MyUseDetailsService myUseDetailsService ;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwt(request);
			if(jwt !=null && jwtTokenProvider.validateToken(jwt)) {
				String username =  jwtTokenProvider.getUserFromToke(jwt);
				UserDetails userDetails = myUseDetailsService.loadUserByUsername("username");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			    SecurityContextHolder.getContext().setAuthentication(authentication); 
				
				
				
			}
			
		} catch (Exception e) {
			logger.error("Can't set user authentication -> Message: {}", e);
		}
		filterChain.doFilter(request, response);
	}



	//lay token từ header của request, và lược bỏ phần kiểu mã hóa
	private String getJwt(HttpServletRequest request) {
			
		String authenHeader  = request.getHeader("Authorization");
			if(authenHeader != null && authenHeader.startsWith("Bearer")) {
				return authenHeader.replace("Bearer", "");			
			};
			
		
		return null;
	}
	
	
}
