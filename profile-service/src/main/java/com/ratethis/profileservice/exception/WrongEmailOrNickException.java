package com.ratethis.profileservice.exception;

public class WrongEmailOrNickException extends RuntimeException{

        public WrongEmailOrNickException() {
            super("Wrong email or nickname");
        }

}
