package org.example.repositories;

import org.example.db.RefreshTokens;
import org.example.db.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokensRepo extends JpaRepository<RefreshTokens, Long> {
    Optional<RefreshTokens> findByToken(String token);
    void deleteByWorker(Workers worker);
}


