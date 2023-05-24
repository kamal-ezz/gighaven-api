package com.kamal.gighaven.util;

import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Helper {
    private final UserService userService;

    @Autowired
    public Helper(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentUser(Principal principal){
        if (principal == null) {
            return null;
        }
        String username = ((UserDetails) principal).getUsername();

        return userService.getByEmail(username);
    }
}
