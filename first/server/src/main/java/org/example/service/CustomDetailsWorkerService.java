package org.example.service;

import org.example.db.Workers;
import org.example.details.CustomDetailsWorkers;
import org.example.repositories.WorkersRepo;
import org.example.security.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomDetailsWorkerService implements UserDetailsService {
    WorkersRepo repo;
    CustomDetailsWorkers workers;

    public CustomDetailsWorkerService(WorkersRepo repo) {
        this.repo = repo;
    }

    public CustomDetailsWorkerService(WorkersRepo repo, CustomDetailsWorkers workers) {
        this.repo = repo;
        this.workers = workers;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Workers worker = repo.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким Email не найден"));
        return new org.springframework.security.core.userdetails.User(
                worker.getEmail(),
                worker.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(worker.getRole().getString()))
        );

    }

    public Workers findWorkerByEmail(String email) throws UsernameNotFoundException{
        Workers worker = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким Email не найден"));
        return worker;
    }


    public UserDetails loadById(Long id){
        Workers worker = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким id не найден"));
        return new org.springframework.security.core.userdetails.User(
                worker.getEmail(),
                worker.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(worker.getRole().getString()))
        );
    }

    public Workers save(CustomDetailsWorkers worker){
        return repo.save(worker.getWorker());
    }

    public Workers create(CustomDetailsWorkers worker){
        if(!repo.existByEmail(worker.getUsername())){ // возвращает email
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(worker);
    }


    public UserDetails getCurrentUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(userName);
    }




}
