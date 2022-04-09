package com.library.library.entities;

import javax.persistence.*;

@Entity
@Table(name="publishing_house")
public class PublishingHouse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
