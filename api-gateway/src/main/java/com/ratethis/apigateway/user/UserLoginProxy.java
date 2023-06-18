package com.ratethis.apigateway.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile")
public interface UserLoginProxy {

    @GetMapping("/RateThis/findUserByNick/{nick}")
    UserLoginInfo getUser(@PathVariable("nick") String nick);

    @GetMapping("/RateThis/findUserToSend/{nick}")
    UserToSend getUserToSend(@PathVariable("nick") String nick);

    @GetMapping("/RateThis/findUserById/{id}")
    UserToSend getUserById(@PathVariable("id") long id);

    @PostMapping(value = "/RateThis/registration", headers = {"Content-Type=application/json"})
    UserCreateResponse regUser(@RequestBody UserRegistrationInfo user);
}
