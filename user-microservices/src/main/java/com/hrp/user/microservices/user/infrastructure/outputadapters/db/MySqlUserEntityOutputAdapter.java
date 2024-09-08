package com.hrp.user.microservices.user.infrastructure.outputadapters.db;

import com.hrp.user.microservices.common.annotation.PersistenceAdapter;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.infrastructure.outputports.CreateUserOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindUserOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class MySqlUserEntityOutputAdapter implements CreateUserOutputPort, FindUserOutputPort {
    private final JpaUserEntityRepository entityRepository;

    @Autowired
    public MySqlUserEntityOutputAdapter(JpaUserEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.from(user);
        return entityRepository.save(userEntity)
                .toDomain();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return entityRepository.findById(username)
                .map(UserEntity :: toDomain);
    }
}
