package com.library.library.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="schopping_carts")
public class ShoppingCart extends WithTimestampsEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Book> books = new HashSet<Book>();

    @ManyToOne
    private ShoppingCartStatus shoppingCartStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(HashSet<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void setShoppingCartStatus(ShoppingCartStatus shoppingCartStatus){
        this.shoppingCartStatus = shoppingCartStatus;
    }

    public ShoppingCartStatus getShoppingCartStatus(){
        return shoppingCartStatus;
    }
}
