package com.ceiba.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

public class ReponseException {

    private HttpStatus status;

    private String message;

    public ReponseException(HttpStatus badRequest, String message) {
        this.status = badRequest;
        this.message = message;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
