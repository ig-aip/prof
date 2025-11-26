package org.example.details;

import jdk.jfr.SettingDefinition;
import org.example.Enums.RoleType;
import org.example.db.Workers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomDetailsWorkers implements UserDetails {
    private Workers worker;

    public CustomDetailsWorkers(Workers worker){
        this.worker = worker;
    }
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

    public String getEmail(){
        return worker.getEmail();
    }

    @SettingDefinition
    public void setRole(RoleType role){ worker.setRole(role);}

    public Workers getWorker(){
        return worker;
    }



}
