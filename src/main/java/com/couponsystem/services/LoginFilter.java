package com.couponsystem.services;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.couponsystem.services.ClientService.ClientType;

import io.jsonwebtoken.JwtException;

@Service
@Transactional
@Scope("prototype")
public class LoginFilter implements Filter {

	@Autowired
	private JwtUtil jwtUtil;

	private ClientType userType;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, java.io.IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("Access-Control-Max-Age", "120");

		String token = req.getHeader("token");

		if (token != null) {
			try {
				jwtUtil.extractAllClaims(token);
				if (this.userType.equals(jwtUtil.extractUserType(token))) {
					chain.doFilter(request, response);
					return;
				} else {
					return;
				}
			} catch (JwtException e) {

				resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token: " + e.getMessage());
				return;
			}

		} else {
			// if we are here we don't have a token
			// check if this is a preflight request
			if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
				System.out.println("this is preflight");
				chain.doFilter(request, response); // pass the request
				return;
			}

			// this is not a preflight request - we simply dont have a token
			resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "you are not logged in");
		}

	}

	public ClientType getUserType() {
		return userType;
	}

	public void setUserType(ClientType userType) {
		this.userType = userType;
	}
}
