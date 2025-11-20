package org.example.Enums;

public enum RoleType {
    ROLE_FRANCHAISER("Франчайзер"),
    ROLE_MECHANIC("Механик"),
    ROLE_REPLENISHING_BOY("Пополнитель");


    public String getString(){
        return  s;
    }

    String s;
    RoleType(String s){
        this.s = s;
    }
}
