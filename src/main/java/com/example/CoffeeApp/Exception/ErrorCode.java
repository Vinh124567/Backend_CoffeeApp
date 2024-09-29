package com.example.CoffeeApp.Exception;

public enum ErrorCode {
    NAME_EXISTED(409, "Already exists"), // Conflict
    NOT_FOUND(404, "Not found"),// Not Found
    UNAUTHENTICATED(400,"sai pat woat");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
