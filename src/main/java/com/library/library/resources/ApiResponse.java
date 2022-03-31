package com.library.library.resources;

public class ApiResponse<T>{

    protected String status;

    protected T data;

    public ApiResponse(String status, T data){
        this.status = status;
        this.data = data;
    }

    public String getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }
}
