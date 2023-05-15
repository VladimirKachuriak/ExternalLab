package org.example.ukrflix.dto;

import org.example.ukrflix.model.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


public class UserDTO {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    @NotBlank
    @Pattern(regexp = "^[A-Z|А-я]{1}[a-z|а-я]{1,10}$", message = "{label.warning.name}")
    private String firstname;

    @NotBlank
    @Pattern(regexp = "^[A-Z|А-я]{1}[a-z|а-я]{1,10}$", message = "{label.warning.name}")
    private String lastname;

    private String email;

    @Pattern(regexp = "^\\+\\d{6,10}$", message = "{label.warning.incorrectPhone}")
    private String phone;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public User toUser() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setBirthday(birthday);
        user.setAccount(0);
        return user;
    }
}