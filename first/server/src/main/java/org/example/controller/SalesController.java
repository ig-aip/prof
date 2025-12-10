package org.example.controller;

import jakarta.validation.Valid;
import org.example.db.Sales;
import org.example.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Sales")
public class SalesController {
    SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody @Valid Sales sale){
        return ResponseEntity.ok().body(salesService.crate(sale));
    }

    @GetMapping
    public List<Sales> findAll(){
        return salesService.getAll();
    }

    @GetMapping("/Last/{days}")
    public List<Sales> findLastDays(@PathVariable Long days){
        return salesService.findAllSalesLastDays(days);
    }
}
