package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Services.BookService;
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
public class CartController {

    private final UserService userService;

    private final BookService bookService;

    private final TelephoneService telephoneService;

    private final WashingMachineService washingMachineService;

    @GetMapping("/cart")
    public String cart(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        model.addAttribute("user", responseUser.data);
        var cartBooks = responseUser.data.getBooks();
        model.addAttribute("cartBooks", cartBooks);
        var responseBook = bookService.getAll();
        model.addAttribute("books", responseBook.data);
        return "Cart/cartView";
    }

    @GetMapping("/cart/buy")
    public String buy(Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        responseUser.data.emptyBooks();
        userService.update(responseUser.data);
        return "redirect:/cart";
    }
}
