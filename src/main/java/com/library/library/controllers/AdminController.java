package com.library.library.controllers;

import com.library.library.dto.AuthorDto;
import com.library.library.dto.BookCreateDto;
import com.library.library.dto.PublishingHouseDto;
import com.library.library.repositories.AuthorRepository;
import com.library.library.repositories.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    @Autowired
    PublishingHouseRepository publishingHouseRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping(path="/authors-and-publishing-houses/manage")
    public String authorsAndPublishingHouseManage(Model model){
        model.addAttribute("authorForm", new AuthorDto());
        model.addAttribute("publishingHouseForm", new PublishingHouseDto());

        return "admin/authors-and-publishing-houses";
    }

    @GetMapping(path="/books/manage")
    public String books(Model model){
        model.addAttribute("bookForm", new BookCreateDto());
        model.addAttribute("publishingHouses", publishingHouseRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());

        return "admin/books";
    }

}
