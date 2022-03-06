package com.vn.VLXD.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vn.VLXD.entities.Account;

import springfox.documentation.spi.service.contexts.SecurityContext;

public class UserLogonService {
	
	public static String getUsername() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if(principal instanceof UserDetailsImpl) {
				return ((UserDetailsImpl) principal).getUsername();
			}else {
				return "anonymous";
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static Account currentUser() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof UserDetailsImpl) {
				return ((UserDetailsImpl) principal).getAccount();
			}else {
				return null;
			}
		} catch (NullPointerException e) {
			return null;
		}
	}
}
