package ru.netology.app_autorizationservice.utils;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.List;

@Validated
@Data
public class User {
    @NotEmpty
    @NotBlank
    private String user;

    @NotEmpty
    @NotBlank
    @Size(max = 12)
    private String password;
    private List<Authorities> authoritiesList;

    public User(String user, String password, List<Authorities> authoritiesList) {
        this.user = user;
        this.password = password;
        this.authoritiesList = authoritiesList;
    }
}