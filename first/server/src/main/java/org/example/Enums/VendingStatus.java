package org.example.Enums;

public enum VendingStatus {
    WORKING("WORKING"),
    TECH_REMONT("TECH_REMONT"),
    CLOSED("CLOSED");


    public String getString(){
        return  s;
    }



    String s;
    VendingStatus(String s){
        this.s = s;
    }

}
