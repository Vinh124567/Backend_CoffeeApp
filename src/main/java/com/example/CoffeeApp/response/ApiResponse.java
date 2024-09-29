package com.example.CoffeeApp.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse<T>{
    private int status;
    private String message;
    private T data;
}