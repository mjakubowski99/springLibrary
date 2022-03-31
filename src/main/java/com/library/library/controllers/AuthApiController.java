package com.library.library.controllers;

import com.library.library.auth.JwtTokenUtil;
import com.library.library.auth.exceptions.NotExistsException;
import com.library.library.auth.exceptions.UserAlreadyExistsException;
import com.library.library.entities.User;
import com.library.library.requests.UserCreateRequest;
import com.library.library.resources.ApiResponse;
import com.library.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController @RequestMapping(path = "api/guest")
public class AuthApiController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    protected UserService userService;

    public AuthApiController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = new JwtTokenUtil();
    }

    @PostMapping("login")
    public ResponseEntity<User> login(String login, String password){
        try{
            Authentication authenticate = authenticationManager
                    .authenticate( new UsernamePasswordAuthenticationToken(login, password) );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateToken(user)
                )
                .body(user);
        }
        catch (BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public ApiResponse<User> register(@RequestBody @Valid UserCreateRequest request) throws UserAlreadyExistsException, NotExistsException {
        return new ApiResponse<User>("success", userService.createUser(request));
    }
}
