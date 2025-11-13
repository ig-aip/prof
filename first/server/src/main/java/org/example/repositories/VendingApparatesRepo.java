package org.example.repositories;

import org.example.db.VendingApparates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingApparatesRepo extends JpaRepository<VendingApparates, Long> {
}
