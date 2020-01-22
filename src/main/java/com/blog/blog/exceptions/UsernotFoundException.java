package com.blog.blog.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernotFoundException extends RuntimeException {
    public UsernotFoundException(String s) {
        super(s);
    }
}
