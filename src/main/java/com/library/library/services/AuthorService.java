package com.library.library.services;

import com.library.library.entities.Author;
import com.library.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getAuthors(Integer page){
        return authorRepository.findAll( PageRequest.of(page, 10) );
    }

    public void create(String name){
        Author author = new Author();
        author.setName(name);

        authorRepository.save(author);
    }

    public void delete(Integer id){
        authorRepository.deleteById(id);
    }
}
