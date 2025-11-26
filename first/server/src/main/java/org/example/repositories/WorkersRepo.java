package org.example.repositories;

import org.example.db.Workers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkersRepo extends JpaRepository<Workers, Long> {
    Optional<Workers> findByEmail(String email);
    Optional<Workers> findById(Long id);
    boolean existsByEmail(String email);


}
