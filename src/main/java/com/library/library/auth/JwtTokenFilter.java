package com.library.library.auth;


import com.library.library.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if( header == null || !header.startsWith("Bearer ") ){
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();

        if( !jwtTokenUtil.validate(token) ){
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = new JwtUserDetailsService().loadUserByUsername( jwtTokenUtil.getUsername(token) );
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
           userDetails, null, userDetails == null ? List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails(request) );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
