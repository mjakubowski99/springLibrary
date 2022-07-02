package com.library.library.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
