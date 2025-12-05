package enams;

public enum RoleType {
    ROLE_FRANCHAISER("Франчайзер"),
    ROLE_MECHANIC("Мезаник"),
    ROLE_REPLENISHING_BOY("мальчик для пополнения");


    public String getString(){
        return  s;
    }

    String s;
    RoleType(String s){
        this.s = s;
    }
}
