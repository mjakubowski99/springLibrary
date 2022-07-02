package com.library.library.controllers;

import com.library.library.auth.AuthUserFacade;
import com.library.library.entities.Book;
import com.library.library.entities.ShoppingCart;
import com.library.library.repositories.UserRepository;
import com.library.library.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/library")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path="/user/shopping-cart/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Book> shoppingCart(Model model){
        return shoppingCartService.userShoppingCart( userRepository.getByEmail(AuthUserFacade.authEmail()) );
    }

    @GetMapping(path="/user/shopping-cart")
    public String index(){
        return "shopping_cart";
    }

    @GetMapping(path="shopping-cart/add")
    public String create(@RequestParam Integer book){
        try{
            shoppingCartService.addBookToCart(
                userRepository.getByEmail(AuthUserFacade.authEmail()),
                book
            );
            return "shopping_cart";
        }
        catch (Exception exception){
            return "shopping_cart";
        }
    }

    @GetMapping(path="shopping-cart/delete")
    public String delete(@RequestParam Integer book){

        try{
            shoppingCartService.deleteBookFromCard(
                    userRepository.getByEmail(AuthUserFacade.authEmail()),
                    book
            );
            return "redirect:/library/user/shopping-cart";
        }
        catch (Exception exception){
            return "redirect:/library/user/shopping-cart";
        }
    }
}
