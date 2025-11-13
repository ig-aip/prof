package org.example.service;

import jakarta.transaction.Transactional;
import org.example.db.VendingApparates;
import org.example.repositories.VendingApparatesRepo;
import org.springframework.stereotype.Service;

@Service
public class VendingApparatesService {
    private final VendingApparatesRepo repo;

    public VendingApparatesService(VendingApparatesRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public VendingApparates create(VendingApparates v){
        return repo.save(v);
    }
}
