package com.ratethis.publicservice.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String name) {
        super("Profile with nickname "+name+" not found");
    }
}
