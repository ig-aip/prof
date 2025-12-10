package org.example.service;

import org.example.db.Sales;
import org.example.repositories.SalesRepo;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class SalesService {
    SalesRepo salesRepo;
    ZoneId zone =  ZoneId.of("Europe/Moscow");

    public SalesService(SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    public Sales crate(Sales sales){
        return salesRepo.save(sales);
    }

    public List<Sales> findAllSalesLastDays(Long days){
        OffsetDateTime ago = OffsetDateTime.now(zone).minusDays(days);
        return salesRepo.findBySoldAtAfter(ago);
    }

    public List<Sales> getAll(){
        return salesRepo.findAll();
    }


}
