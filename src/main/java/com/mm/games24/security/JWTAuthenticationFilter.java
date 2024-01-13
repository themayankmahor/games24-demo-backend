package com.mm.games24.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//1. Get TOKEN
		String requestToken = request.getHeader("Authorization");
		
		//bearer 321423nnnjnjnasd
		System.out.println(requestToken);
		
		String username = null;
		
		String token = null;
		
		if (requestToken != null && requestToken.startsWith("Bearer"))
		{
			token = requestToken.substring(7);
			
			try {
				
				username = jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				
				System.out.println("Unable to get jwt token");
			}catch (ExpiredJwtException e) {
				//
				System.out.println("JWT token has expired");
			}catch (MalformedJwtException e) {
				//
				System.out.println("Invalid JWT");
			}
		}
		else
		{
			System.out.println("JWT token does not begin with Bearer");
		}
		
		///Once we get the token, now validate
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			//
			if (jwtTokenHelper.validateToken(token, userDetails))
			{
				//authentication
				UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.println("Invalid JWT token");
			}
		}
		else
		{
			System.out.println("Username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
	}

}
