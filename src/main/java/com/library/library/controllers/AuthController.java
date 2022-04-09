package com.library.library.controllers;

import com.library.library.auth.AuthUserFacade;
import com.library.library.auth.LibraryUserService;
import com.library.library.dto.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private LibraryUserService libraryUserService;

    @GetMapping(path="/login")
    public String login(){
        if( AuthUserFacade.isAuthenticated() ){
            return "redirect:/library/home";
        }
        return "auth/login";
    }

    @GetMapping(path="/register")
    public String registerView(Model model){
        if( AuthUserFacade.isAuthenticated() ){
            return "redirect:/library/home";
        }

        model.addAttribute("registerForm", new UserCreateDto());
        return "auth/register";
    }

    @PostMapping(path="/register", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public String register(@Valid @ModelAttribute("registerForm") UserCreateDto registerForm, BindingResult bindingResult, Model model) throws Exception{
        if(bindingResult.hasErrors()){
            return "auth/register";
        }

        registerForm.setRole("User");

        try{
            libraryUserService.register(registerForm);
        } catch (Exception exception){
            bindingResult.addError(new ObjectError("email", exception.getMessage()));
            return "auth/register";
        }

        return "redirect:login";
    }
}
