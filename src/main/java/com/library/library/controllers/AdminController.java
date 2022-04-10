package com.library.library.controllers;

import com.library.library.dto.AuthorDto;
import com.library.library.dto.BookCreateDto;
import com.library.library.dto.PublishingHouseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/admin")
public class AdminController {

    @GetMapping(path="/authors-and-publishing-houses/manage")
    public String authorsAndPublishingHouseManage(Model model){
        model.addAttribute("authorForm", new AuthorDto());
        model.addAttribute("publishingHouseForm", new PublishingHouseDto());

        return "admin/authors-and-publishing-houses";
    }

    @GetMapping(path="/books/manage")
    public String books(Model model){
        model.addAttribute("bookForm", new BookCreateDto());

        return "admin/books";
    }

}
