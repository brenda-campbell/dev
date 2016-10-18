package com.mcreceiverdemo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.mcreceiverdemo.mc.ClientService;

//@Component
public class CustomLogoutHandler {//implements LogoutHandler {
	
    /*@Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){ 
			auth.setAuthenticated(false);
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		SecurityContextHolder.clearContext();
    }*/
	
	public static boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
}