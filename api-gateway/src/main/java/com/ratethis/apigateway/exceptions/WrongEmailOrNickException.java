package com.ratethis.apigateway.exceptions;

public class WrongEmailOrNickException extends RuntimeException{

        public WrongEmailOrNickException() {
            super("Wrong email or nickname");
        }

}
