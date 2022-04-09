package com.library.library.auth;

import com.library.library.entities.Role;
import com.library.library.entities.User;
import com.library.library.repositories.RoleRepository;
import com.library.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
@Transactional
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);

        if( user == null ){
            return new org.springframework.security.core.userdetails.User(
                " ", " ", true, true, true, true,
                getGrantedAuthorities(Arrays.asList(roleRepository.getRoleByName("User")))
            );
        }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), true, true, true, true,
            getGrantedAuthorities(user.getRoles())
        );
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role : roles ){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "Admin > User";
        roleHierarchy.setHierarchy(hierarchy);

        return roleHierarchy;
    }

    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());

        return expressionHandler;
    }

}
