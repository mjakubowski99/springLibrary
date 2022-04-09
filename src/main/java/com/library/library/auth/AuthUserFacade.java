package com.library.library.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUserFacade {

    public static boolean isAuthenticated(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())){
            return false;
        }
        return auth.isAuthenticated();
    }
}
