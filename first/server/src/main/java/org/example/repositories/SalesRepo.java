package org.example.repositories;

import org.example.db.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Long> {
    List<Sales> findBySoldAtAfter(OffsetDateTime since);
}
