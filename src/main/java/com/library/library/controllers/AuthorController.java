package com.library.library.controllers;

import com.library.library.dto.AuthorDto;
import com.library.library.entities.Author;
import com.library.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/admin")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(path="/authors/ajax")
    @ResponseBody
    public Iterable<Author> authors(@RequestParam Integer page){
        return authorService.getAuthors(page);
    }

    @PostMapping(path="/authors")
    public String create(@ModelAttribute("authorForm") AuthorDto authorDto){
        authorService.create(authorDto.getName());

        return "redirect:/admin/authors-and-publishing-houses/manage";
    }

    @GetMapping(path="/authors/delete")
    public String delete(@RequestParam Integer id){
        authorService.delete(id);

        return "redirect:/admin/authors-and-publishing-houses/manage";
    }

}
