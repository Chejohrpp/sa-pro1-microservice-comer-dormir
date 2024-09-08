package com.hrp.user.microservices.client.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientEntityRepository extends JpaRepository<ClientEntity, String> {
}
