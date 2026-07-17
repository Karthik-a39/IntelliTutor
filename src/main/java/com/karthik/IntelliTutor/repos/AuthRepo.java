package com.karthik.IntelliTutor.repos;

import com.karthik.IntelliTutor.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<AuthEntity,Long> {
    Optional<AuthEntity> findByEmail(String email);
}
