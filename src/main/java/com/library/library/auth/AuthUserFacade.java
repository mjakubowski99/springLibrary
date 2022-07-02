package com.library.library.auth;

import com.library.library.entities.User;
import com.library.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public class AuthUserFacade {

    public static boolean isAuthenticated(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())){
            return false;
        }
        return auth.isAuthenticated();
    }

    public static String authEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if( principal instanceof UserDetails){
            return  ((UserDetails)principal).getUsername();
        }
        return null;
    }
}
