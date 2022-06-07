package ru.netology.app_autorizationservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.app_autorizationservice.utils.Authorities;
import ru.netology.app_autorizationservice.exceptions.InvalidCredentials;
import ru.netology.app_autorizationservice.exceptions.UnauthorizedUser;
import ru.netology.app_autorizationservice.services.AuthorizationService;
import ru.netology.app_autorizationservice.utils.User;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) {
        return service.getAuthorities(user.getUser(), user.getPassword());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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