package com.library.library.controllers;

import com.library.library.entities.Author;
import com.library.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
@RequestMapping(path="/library")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path="/authors/ajax")
    @ResponseBody
    public Iterable<Author> authors(@RequestParam Integer page){
        return authorService.getAuthors(page);
    }

    @GetMapping(path="/authors")
    public String index(){
        return "author/index";
    }


}
