package com.hibicode.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistsException extends BusinessException {

    public BeerAlreadyExistsException() {
        super("beers-5", HttpStatus.BAD_REQUEST);
    }
}
