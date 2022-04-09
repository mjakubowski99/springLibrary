package com.library.library.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class UserCreateDto {

    @NotBlank(message="Pole email jest wymagane")
    @Email
    private String email;

    @NotBlank(message="Pole username jest wymagane")
    @Size(min=8, max=10, message = "Długość pola username musi zawierać się w zakresie od 8 do 10")
    private String username;

    @NotBlank(message="Pole hasło jest wymagane")
    @Size(min=10, max=100, message = "Hasło musi mieć długość od 10 do 100 znaków")
    private String password;

    private String role;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole(){
        return role;
    }
}
