package org.example.service;

import org.example.Enums.RoleType;
import org.example.db.Workers;
import org.example.details.CustomDetailsWorkers;
import org.example.repositories.WorkersRepo;
import org.example.security.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomDetailsWorkerService implements UserDetailsService {
    WorkersRepo repo;
    CustomDetailsWorkers workers;


    public CustomDetailsWorkerService(WorkersRepo repo, CustomDetailsWorkers workers) {
        this.repo = repo;
        this.workers = workers;
    }

    @Override
    public CustomDetailsWorkers loadUserByUsername(String userName) throws UsernameNotFoundException {
        Workers worker = repo.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким Email не найден"));
        return new CustomDetailsWorkers(worker);

    }

    public Workers findWorkerByEmail(String email) throws UsernameNotFoundException{
        Workers worker = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким Email не найден"));
        return worker;
    }


    public CustomDetailsWorkers loadById(Long id){
        Workers worker = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким id не найден"));
        return new CustomDetailsWorkers(worker);
    }

    public Workers save(CustomDetailsWorkers worker){
        return repo.save(worker.getWorker());
    }

    public Workers create(CustomDetailsWorkers worker){
        if(repo.existsByEmail(worker.getUsername())){ // возвращает email
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(worker);
    }


    public CustomDetailsWorkers getCurrentUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(userName);
    }

    @Deprecated
    public void getAdmin(){
        var user = getCurrentUser();
        user.getWorker().setRole(RoleType.ROLE_FRANCHAISER);
    }



}
