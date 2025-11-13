package org.example.controller;


import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.example.db.VendingApparates;
import org.example.repositories.VendingApparatesRepo;
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


    public VendingApparatesController(VendingApparatesRepo repo) {
        this.repo = repo;
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

    @GetMapping
    public List<VendingApparates>  getALL(){
        System.out.println("getAll");
        List<VendingApparates> list = repo.findAll();
        return list;
    }
}
