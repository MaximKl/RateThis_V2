package com.ratethis.apigateway.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

    private final UserLoginProxy proxy;

    @Override
    public Mono findByUsername(String username) {
        return Mono.fromCallable(() -> proxy.getUser(username)).subscribeOn(Schedulers.boundedElastic());
    }




}