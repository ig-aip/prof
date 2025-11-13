package org.example.details;

import org.example.db.Workers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomDetailsWorkers implements UserDetails {
    private Workers worker;

    public void setWorker(Workers worker) {
        this.worker = worker;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(worker.getRole().getString() ) );
    }

    @Override
    public String getPassword() {
        return worker.getPasswordHash();
    }

    @Override
    public String getUsername(){
        return worker.getEmail();
    }


    public Workers getWorker(){
        return worker;
    }



}
