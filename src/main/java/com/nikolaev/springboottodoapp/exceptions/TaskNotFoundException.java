package com.nikolaev.springboottodoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nikolaev Artem
 **/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(){
        super();
    }

    public TaskNotFoundException(String message){
        super(message);
    }
}
