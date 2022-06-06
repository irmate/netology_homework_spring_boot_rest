package ru.netology.app_autorizationservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.app_autorizationservice.utils.Authorities;
import ru.netology.app_autorizationservice.utils.User;
import ru.netology.app_autorizationservice.exceptions.InvalidCredentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {

    private List<User> userList;

    public UserRepository() {
        userList = Arrays.asList(
                new User("Jon", "JonBeen432", Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)),
                new User("Garry", "Garryisgreat", List.of(Authorities.READ)),
                new User("Berta", "BIGBERTA", new ArrayList<>())
        );
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authoritiesList;
        try {
            authoritiesList = userList.stream()
                    .filter(value -> value.getUser().equals(user) && value.getPassword().equals(password))
                    .findAny().orElseThrow().getAuthoritiesList();
        } catch (NoSuchElementException e) {
            throw new InvalidCredentials("The entered data is not correct");
        }
        return authoritiesList;
    }
}