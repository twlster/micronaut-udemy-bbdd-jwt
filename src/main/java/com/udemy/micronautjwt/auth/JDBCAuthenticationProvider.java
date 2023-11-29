package com.udemy.micronautjwt.auth;

//import edu.udemy.micronaut.constants.Constants;

import com.udemy.micronautjwt.auth.persistence.entity.UserEntity;
import com.udemy.micronautjwt.auth.persistence.repository.UserEntityRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class JDBCAuthenticationProvider implements AuthenticationProvider<HttpRequest<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(JDBCAuthenticationProvider.class);

    private final UserEntityRepository userEntityRepository;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            LOG.debug("User {} tries to login", authenticationRequest.getIdentity());

            final String email = (String) authenticationRequest.getIdentity();

            Optional<UserEntity> user = userEntityRepository.findByEmail(email);

            if (user.isPresent()) {
                LOG.debug("User {} found", email);
                final String password = (String) authenticationRequest.getSecret();

                if (user.get().getPassword().equals(password)) {
                    LOG.debug("User {} logged in", email);

                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("language", "en");

                    List<String> roles = new ArrayList<>();
                    roles.add("ROLE_USER");

                    emitter.next(AuthenticationResponse.success((String)authenticationRequest.getIdentity(), roles, attributes));
                    emitter.complete();
                }else{
                    LOG.debug("User {} not logged in", email);
                }
            } else {
                LOG.debug("User {} not found", email);
                emitter.error(AuthenticationResponse.exception());
            }
            emitter.error(AuthenticationResponse.exception("Wrong username and/or password...."));
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
