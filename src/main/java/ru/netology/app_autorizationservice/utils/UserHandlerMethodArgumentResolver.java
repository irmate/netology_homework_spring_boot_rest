package ru.netology.app_autorizationservice.utils;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.app_autorizationservice.utils.User;

public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        String user = webRequest.getParameter("user");
        String password = webRequest.getParameter("password");

        if (isNotSet(user)) {
            user = "defaultUser";
        }

        if (isNotSet(password)) {
            password = "defaultPassword";
        }

        return new User(user, password, null);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}