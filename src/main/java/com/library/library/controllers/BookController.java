package com.library.library.controllers;

import com.library.library.dto.BookCreateDto;
import com.library.library.entities.Book;
import com.library.library.exceptions.AuthorNotFoundException;
import com.library.library.exceptions.PublishingHouseNotFoundException;
import com.library.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(path="/admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path="/books/ajax")
    public Iterable<Book> books(@RequestParam Integer page){
        return bookService.books(page);
    }

    @PostMapping(path="/books", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String create(@Valid @ModelAttribute("bookForm") BookCreateDto bookCreateDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "admin/books";
        }

        try{
            bookService.create(bookCreateDto);
        } catch (IOException ioException){
            bindingResult.addError( new ObjectError("file", "Problem z uploadem pliku"));
        } catch (AuthorNotFoundException exception){
            bindingResult.addError( new ObjectError("author", "Nie odnaleziono takiego autora"));
        } catch (PublishingHouseNotFoundException exception){
            bindingResult.addError( new ObjectError("publishingHouse", "Nie odnaleziono takiego wydawnictwa"));
        }

        if( bindingResult.hasErrors()){
            return "admin/books";
        }

        return "redirect:/admin/books";
    }
}
