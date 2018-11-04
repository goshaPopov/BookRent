package ru.popov.book_rent.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.stereotype.Component;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.services.UserService;

import java.io.Serializable;
import java.util.Optional;


@Component("permissionEvaluator")
public class PermissionEvaluatorImpl implements PermissionEvaluator{

    @Autowired
    private UserService userService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if (authentication == null) {
            return false;
        }

        String login = (String) authentication.getPrincipal();

        if (permission.toString().equals("isThisUser")) {
            return isItUserBookList(targetDomainObject, login);
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }

    private boolean isItUserBookList(Object target, String principal){
        boolean allow = false;

        Optional<User> userTarget = userService.findById((Long) target);
        Optional<User> userLogged = userService.findByLogin(principal);

        if ((userTarget.isPresent() && userLogged.isPresent())
                && userTarget.get().equals(userLogged.get())) {
            allow = true;
        }

        return allow;
    }
}
