package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("userAuthorized", false);
        }
        return "User/loginView";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("userAuthorized", false);
        }
        return "User/registrationView";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        var responseBool = userService.create(user);
        if (responseBool.getStatusCode() == StatusCode.Ok) {
            return "redirect:/login";
        }
        else {
            model.addAttribute("success", false);
            model.addAttribute("errorMessage", responseBool.description);
            model.addAttribute("user", new User());
            return "User/registrationView";
        }
    }
}
