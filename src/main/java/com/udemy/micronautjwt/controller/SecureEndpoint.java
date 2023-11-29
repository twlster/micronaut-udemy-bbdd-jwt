package com.udemy.micronautjwt.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/secured")
public class SecureEndpoint {

    @Get("/status")
    public List<Object> getStatus(Principal principal){
        Authentication authentication = (Authentication) principal;

        return List.of(principal.getName(), authentication.getRoles(), authentication.getAttributes());
    }
}
