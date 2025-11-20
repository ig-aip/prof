package org.example.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignInRequest {
    @Size(min = 5, max = 255, message = "Почта должна содержать от 5 до 255 символов")
    @Email(message = "Адрес электронной почты должен быть в формате igore@babich.vl")
    @NotBlank(message = "Почта не может быть пустой")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(max = 255, message = "пароль должен быть меньше 255 символов")
    private String password;


    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
}



