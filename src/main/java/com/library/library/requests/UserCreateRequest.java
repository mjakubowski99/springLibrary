package com.library.library.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class UserCreateRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=8, max=10)
    private String username;

    @Size(min=10, max=100)
    private String password;

    @NotBlank
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
