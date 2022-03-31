package com.library.library.auth.exceptions;

public class UserAlreadyExistsException extends Exception{

    @Override
    public String getMessage() {
        return "This user already exists";
    }
}
