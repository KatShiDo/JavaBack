package com.mirea.JavaBack.Controllers.Admin;

import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.BookService;
import com.mirea.JavaBack.Services.TelephoneService;
import com.mirea.JavaBack.Services.UserService;
import com.mirea.JavaBack.Services.WashingMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private UserService userService;

    private BookService bookService;

    private TelephoneService telephoneService;

    private WashingMachineService washingMachineService;

    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("userAuthorized", false);
        }
        var responseBook = bookService.getAll();
        model.addAttribute("books", responseBook.data);
        return "Admin/adminView";
    }
}
