package com.library.library.entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private String name;

    private Integer price;

    private String photo;

    @ManyToOne
    private PublishingHouse publishingHouse;

    @ManyToOne
    private Author author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPhoto() {
        return photo;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, name, price, photo, publishingHouse, author);
    }
}
