package com.ratethis.chatservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "api-gateway")
public interface ValidationProxy {

    @GetMapping("/validateChatMessage")
    ResponseEntity getValidation(@RequestHeader("token") String token);

}
