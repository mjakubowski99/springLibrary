package com.library.library.entities;

import javax.persistence.*;

@Entity
@Table(name="shopping_cart_statuses")
public class ShoppingCartStatus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
