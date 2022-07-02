package com.library.library.services;

import com.library.library.entities.Book;
import com.library.library.entities.ShoppingCart;
import com.library.library.entities.User;
import com.library.library.exceptions.BookOutOfStockException;
import com.library.library.exceptions.BookNotFoundException;
import com.library.library.repositories.BookRepository;
import com.library.library.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    BookRepository bookRepository;

    public void addBookToCart(User user, Integer bookId) throws BookOutOfStockException, BookNotFoundException{
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = optionalBook.stream().findFirst().orElse(null);
        if( book == null ){
            throw new BookNotFoundException();
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId());

        if( shoppingCart == null ){
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartRepository.save(shoppingCart);
        }

        if( book.getQuantity() == 0 ){
            throw new BookOutOfStockException();
        }

        book.setQuantity( book.getQuantity() - 1 );
        shoppingCart.addBook(book);

        bookRepository.save(book);
        shoppingCartRepository.save(shoppingCart);
    }
}
