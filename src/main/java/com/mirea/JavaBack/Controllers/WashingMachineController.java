package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.TelephoneService;
import com.mirea.JavaBack.Services.UserService;
import com.mirea.JavaBack.Services.WashingMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WashingMachineController {

    private final WashingMachineService washingMachineService;
    private final UserService userService;

    @GetMapping("/washingMachines")
    public String washingMachines(Principal principal, Model model) {
        var responseWashingMachine = washingMachineService.getAll();
        if (responseWashingMachine.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("washingMachines", responseWashingMachine.data);
            model.addAttribute("washingMachinesReceived", true);
        }
        else {
            model.addAttribute("washingMachinesError", responseWashingMachine.description);
            model.addAttribute("washingMachinesReceived", false);
        }
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("userAuthorized", false);
        }
        return "Products/washingMachineView";
    }
}
