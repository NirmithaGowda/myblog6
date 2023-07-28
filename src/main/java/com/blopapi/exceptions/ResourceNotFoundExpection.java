package com.blopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND) //404
public class ResourceNotFoundExpection  extends RuntimeException{

    private  long id;

    public ResourceNotFoundExpection(long id){
        super(" Resource Not Found  for id:" +id);
    }
}