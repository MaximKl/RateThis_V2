package com.ratethis.apigateway.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class MyErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        String body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        if (body.equals("mail_exist")) {
            return new AlreadyExistException("this email");
        }
        if (body.equals("nick_exist")) {
            return new AlreadyExistException("this name");
        }
        if (body.equals("wrong_nick_or_mail")) {
            return new WrongEmailOrNickException();
        }
        if (body.equals("valid")) {
            return new ValidationException();
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

}