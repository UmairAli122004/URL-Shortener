package com.UrlShortener.Controller;

import com.UrlShortener.Entities.User;
import com.UrlShortener.Repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {
    private final UserRepository userRepository;
    public SecurityUtils(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated()){
            String email = authentication.getName();
            return userRepository.findByEmail(email).orElseThrow(null);
        }
        return null;
    }

    public Long getCurrentUserId(){
        User user = getCurrentUser();
        return user!=null? user.getId() : null;
    }
}
