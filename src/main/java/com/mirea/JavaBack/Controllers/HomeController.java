package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("userAuthorized", false);
        }
        return "indexView";
    }
}
