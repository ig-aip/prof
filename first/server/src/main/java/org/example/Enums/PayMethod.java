package org.example.Enums;

public enum PayMethod {
    CARD("CARD"),
    QR("QR"),
    CASH("CASH");

    String s;
    PayMethod(String s) { this.s = s;}

    public String getString(){ return s;}
}
