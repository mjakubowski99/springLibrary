package com.library.library.controllers;

import com.library.library.auth.AuthUserFacade;
import com.library.library.repositories.UserRepository;
import com.library.library.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/library")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="shoppingCart/add")
    public String create(@RequestParam Integer book){
        try{
            shoppingCartService.addBookToCart(
                userRepository.getByEmail(AuthUserFacade.authEmail()),
                book
            );
            return "index";
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return "index";
        }
    }
}
