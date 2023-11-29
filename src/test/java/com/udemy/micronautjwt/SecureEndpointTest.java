package com.udemy.micronautjwt;

import com.udemy.micronautjwt.util.Constants;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.token.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MicronautTest
class SecureEndpointTest implements ILogin{

    private static final Logger LOG = LoggerFactory.getLogger(SecureEndpointTest.class);

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void getStatus() {
        BearerAccessRefreshToken token = login(client, Constants.USERNAME, Constants.PASSWORD);
        var response = client.toBlocking().exchange(HttpRequest.GET("/secured/status").bearerAuth(token.getAccessToken()), String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
        LOG.debug(response.getBody().get());
    }


}
