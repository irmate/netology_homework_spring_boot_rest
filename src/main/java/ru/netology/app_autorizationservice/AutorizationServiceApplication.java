package ru.netology.app_autorizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.app_autorizationservice.utils.UserHandlerMethodArgumentResolver;

import java.util.List;

@SpringBootApplication
public class AutorizationServiceApplication implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserHandlerMethodArgumentResolver());
    }

    public static void main(String[] args) {
        SpringApplication.run(AutorizationServiceApplication.class, args);
    }

}