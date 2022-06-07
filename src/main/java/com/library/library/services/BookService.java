package com.library.library.services;

import com.library.library.dto.BookCreateDto;
import com.library.library.entities.Author;
import com.library.library.entities.Book;
import com.library.library.entities.PublishingHouse;
import com.library.library.exceptions.AuthorNotFoundException;
import com.library.library.exceptions.PublishingHouseNotFoundException;
import com.library.library.files.FileUploadUtil;
import com.library.library.repositories.AuthorRepository;
import com.library.library.repositories.BookRepository;
import com.library.library.repositories.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Book> books(Integer page){
        return bookRepository.findAll( PageRequest.of(page, 10) );
    }

    public Iterable<Book> booksAll(){
        return bookRepository.findAll();
    }

    public void create(BookCreateDto bookCreateDto) throws IOException, PublishingHouseNotFoundException, AuthorNotFoundException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(bookCreateDto.getPhoto().getOriginalFilename()));

        Book book = new Book();
        book.setName( bookCreateDto.getName() );
        book.setPrice( bookCreateDto.getPrice() );
        book.setPhoto(fileName);
        book.setQuantity( bookCreateDto.getQuantity() );

        Optional<Author> author = authorRepository.findById( bookCreateDto.getAuthor() );
        if( author.isEmpty()){
            throw new AuthorNotFoundException();
        }
        Optional<PublishingHouse> publishingHouse = publishingHouseRepository.findById( bookCreateDto.getPublishingHouse() );
        if( publishingHouse.isEmpty() ){
            throw new PublishingHouseNotFoundException();
        }

        book.setAuthor( author.stream().findFirst().orElse(null) );
        book.setPublishingHouse( publishingHouse.stream().findFirst().orElse(null) );

        Book savedBook = bookRepository.save(book);

        String uploadDir = "src/main/resources/static/book-photos/"+book.getId().toString();
        FileUploadUtil.saveFile(uploadDir, fileName, bookCreateDto.getPhoto());
    }

    public void delete(Integer id){
        bookRepository.deleteById(id);
    }
}
