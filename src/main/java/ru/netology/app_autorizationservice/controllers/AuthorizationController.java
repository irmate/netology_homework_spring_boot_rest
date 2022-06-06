package ru.netology.app_autorizationservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.app_autorizationservice.utils.Authorities;
import ru.netology.app_autorizationservice.exceptions.InvalidCredentials;
import ru.netology.app_autorizationservice.exceptions.UnauthorizedUser;
import ru.netology.app_autorizationservice.services.AuthorizationService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<String> invalidCredentialsException(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<String> unauthorizedUserException(UnauthorizedUser e) {
        String msg = e.getMessage();
        System.out.println(msg);
        return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
    }
}