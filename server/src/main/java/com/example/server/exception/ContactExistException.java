package com.example.server.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactExistException {
    String message;

    public ContactExistException(String message){
        this.message = message;
    }
}
