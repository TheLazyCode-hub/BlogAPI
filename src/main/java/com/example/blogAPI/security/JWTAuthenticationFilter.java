package com.example.blogAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
		// this class is called every time api request is hit
	@Autowired
	JWTTokenHelper jwtTokenHelper;
	
	@Autowired
	CustomUserDetailsService userdetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			// get token
		String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);
		String username = null;
		String token = null;
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = jwtTokenHelper.extractUsername(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal argument passed!!!");
			} catch (ExpiredJwtException e) {
				System.out.println("Expired jwt token!!!");
			} catch (MalformedJwtException e) {
				System.out.println("Malformed jwt exception!!!");
			}
		}else {
			System.out.println("Not Start with bearer");
		}
		
		//Once we get token now validate it
		if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userdDetails = userdetailService.loadUserByUsername(username);
			if(jwtTokenHelper.validateToken(token, userdDetails)) {
				UsernamePasswordAuthenticationToken userPasswordauthToken = new UsernamePasswordAuthenticationToken(userdDetails,null, userdDetails.getAuthorities());
				userPasswordauthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordauthToken);
			}else {
				System.out.println("invalid token");
			}
		}else {
			System.out.println("Username is null or context missing");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
