package com.hrp.user.microservices.user.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserEntityRepository extends JpaRepository<UserEntity, String> {
}
