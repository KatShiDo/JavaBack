package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.BookService;
import com.mirea.JavaBack.Services.TelephoneService;
import com.mirea.JavaBack.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TelephoneController {

    private final TelephoneService telephoneService;
    private final UserService userService;

    @GetMapping("/telephones")
    public String telephones(Principal principal, Model model) {
        var responseTelephone = telephoneService.getAll();
        if (responseTelephone.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("telephones", responseTelephone.data);
            model.addAttribute("telephonesReceived", true);
        }
        else {
            model.addAttribute("telephonesError", responseTelephone.description);
            model.addAttribute("telephonesReceived", false);
        }
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("userAuthorized", false);
        }
        return "Products/telephoneView";
    }
}
