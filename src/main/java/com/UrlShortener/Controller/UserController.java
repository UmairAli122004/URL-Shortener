package com.UrlShortener.Controller;

import com.UrlShortener.DTOs.CreateUserCmd;
import com.UrlShortener.DTOs.RegisterUserRequest;
import com.UrlShortener.Enums.Role;
import com.UrlShortener.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegisterUserRequest("","",""));
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") RegisterUserRequest registerRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            var cmd = new CreateUserCmd(
                    registerRequest.email(),
                    registerRequest
                            .password(),
                    registerRequest.name(),
                    Role.USER
            );

            userService.createUser(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
}
