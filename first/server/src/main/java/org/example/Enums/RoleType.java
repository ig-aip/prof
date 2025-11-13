package org.example.Enums;

public enum RoleType {
    FRANCHAISER("Франчайзер"),
    MECHANIC("Механик"),
    REPLENISHING_BOY("Пополнитель");


    public String getString(){
        return  s;
    }

    String s;
    RoleType(String s){
        this.s = s;
    }
}
