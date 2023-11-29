package com.udemy.micronautjwt.dataprovider;

import com.udemy.micronautjwt.auth.persistence.entity.UserEntity;
import com.udemy.micronautjwt.auth.persistence.repository.UserEntityRepository;
import com.udemy.micronautjwt.util.Constants;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class TestDataProvider {

    private final UserEntityRepository userEntityRepository;

    @EventListener
    public void init(StartupEvent startupEvent) {
        if (userEntityRepository.findByEmail(Constants.USERNAME).isEmpty()) {
            final UserEntity userEntity = new UserEntity();
            userEntity.setEmail(Constants.USERNAME);
            userEntity.setPassword(Constants.PASSWORD);
            userEntityRepository.save(userEntity);
        }
    }

}
