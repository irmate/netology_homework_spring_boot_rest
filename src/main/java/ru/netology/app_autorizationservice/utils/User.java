package ru.netology.app_autorizationservice.utils;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String user;
    private String password;
    private List<Authorities> authoritiesList;

    public User(String user, String password, List<Authorities> authoritiesList) {
        this.user = user;
        this.password = password;
        this.authoritiesList = authoritiesList;
    }
}