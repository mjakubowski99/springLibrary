package com.library.library.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId(){ return id; }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
