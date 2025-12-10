package org.example.controller;


import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.example.authentication.DiagramInfo;
import org.example.db.Sales;
import org.example.db.VendingApparates;
import org.example.repositories.VendingApparatesRepo;
import org.example.service.VendingApparatesService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vending")
public class VendingApparatesController {
    private final VendingApparatesRepo repo;
    private VendingApparatesService service;

    public VendingApparatesController(VendingApparatesRepo repo, VendingApparatesService service) {
        this.repo = repo;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<VendingApparates> create(@RequestBody @Valid VendingApparates v){
        System.out.println("post");
        VendingApparates saved = repo.save(v);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping("/diagram")
    public DiagramInfo getDiagramInfo(){
        return service.getDiagramInfo();
    }

    @GetMapping
    public List<VendingApparates>  getALL(){
        System.out.println("getAll");
        System.out.println("PROCENT: " + service.getVendingApparatesNetworkEffectivnessPercent());
        List<VendingApparates> list = repo.findAll();
        return list;
    }


}
