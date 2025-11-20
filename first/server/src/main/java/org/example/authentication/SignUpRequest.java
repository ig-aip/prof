package org.example.authentication;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.Enums.RoleType;

public class SignUpRequest {

    @Size(min = 1, max = 255, message = "Имя должно содержать от 1 до 255 символов")
    @NotBlank(message = "Имя не можеть быть пустым")
    private String firstName;

    @Size(min = 1, max = 255, message = "Фамилия должна содержать от 1 до 255 символов")
    @NotBlank(message = "Фамилия не можеть быть пустой")
    private String secondName;

    @Size(max = 255, message = "Отчество должно содержать до 255 символов")
    private String thirdName;


    @Size(min = 5, max = 255, message = "Почта должна содержать от 5 до 255 символов")
    @Email(message = "Адрес электронной почты должен быть в формате igore@babich.vl")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    private String email;

    @Size(min = 12, max = 13, message = "Неверно набранный номер")
    @NotBlank(message = "Телефон не может быть пустым")
    private String phone;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(max = 255, message = "пароль должен быть меньше 255 символов")
    private String password;

    private RoleType role = RoleType.ROLE_REPLENISHING_BOY;

    public SignUpRequest(String firstName, String secondName, String thirdName, String email, String phone, String password, RoleType role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
