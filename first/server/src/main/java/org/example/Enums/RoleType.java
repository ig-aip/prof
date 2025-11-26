package org.example.Enums;

public enum RoleType {
    ROLE_FRANCHAISER("ROLE_FRANCHAISER"),
    ROLE_MECHANIC("ROLE_MECHANIC"),
    ROLE_REPLENISHING_BOY("ROLE_REPLENISHING_BOY");


    public String getString(){
        return  s;
    }

    String s;
    RoleType(String s){
        this.s = s;
    }
}
