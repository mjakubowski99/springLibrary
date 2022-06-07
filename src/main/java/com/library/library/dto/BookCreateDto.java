package com.library.library.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookCreateDto {

    @NotBlank(message = "Pole nazwa nie może byc puste")
    @Size(max=256, message = "Maksymalna długość nazwy to 256 znaków")
    private String name;

    @Min(value = 1, message = "Cena musi być conajmniej wartością 1")
    private Integer price;

    @Min(value = 0, message = "Ilość musi wynosić conajmniej 0")
    private Integer quantity;

    private Integer publishingHouse;

    private Integer author;

    private MultipartFile photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishingHouse(Integer publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getPublishingHouse() {
        return publishingHouse;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getAuthor() {
        return author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public MultipartFile getPhoto() {
        return photo;
    }
}
