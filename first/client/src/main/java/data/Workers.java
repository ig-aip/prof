package data;


import enams.RoleType;

import java.time.LocalDateTime;

public class Workers {

    private long id;


    private String firstName;


    private String secondName;


    private String thirdName;


    private String email;


    private String phone;


    private RoleType role;


    LocalDateTime createdAt;


    private String passwordHash;


    public Workers() {}

    public Workers(String firstName, String secondName, String thirdName, String email, String phone, String passwordHash, RoleType role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
    }
    public Workers(String firstName, String secondName, String thirdName, String email, String phone, String passwordHash, String role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = RoleType.valueOf(role);
    }


    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
