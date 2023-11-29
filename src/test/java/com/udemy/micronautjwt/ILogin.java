package com.udemy.micronautjwt;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import org.junit.jupiter.api.Assertions;

public interface ILogin {

    default BearerAccessRefreshToken login(HttpClient client, String user, String pwd) {
        final UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials(user, pwd);

        var login = HttpRequest.POST("/login", credentials);
        var loginResponse = client.toBlocking().exchange(login, BearerAccessRefreshToken.class);

        Assertions.assertEquals(HttpStatus.OK, loginResponse.getStatus());
        Assertions.assertEquals(user, loginResponse.getBody().get().getUsername());

        return loginResponse.getBody().get();
    }
}
