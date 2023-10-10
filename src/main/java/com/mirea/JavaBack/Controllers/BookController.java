package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Entities.Book;
import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.BookService;
import com.mirea.JavaBack.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BookController {

    private BookService bookService;
    private UserService userService;

    @GetMapping("/books")
    public String books(Principal principal, Model model) {
        var responseBook = bookService.getAll();
        if (responseBook.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("books", responseBook.data);
            model.addAttribute("booksReceived", true);
        }
        else {
            model.addAttribute("booksError", responseBook.description);
            model.addAttribute("booksReceived", false);
        }
        var responseUser = userService.getByPrincipal(principal);
        if (responseUser.getStatusCode() == StatusCode.Ok) {
            model.addAttribute("user", responseUser.data);
            model.addAttribute("userAuthorized", true);
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("userAuthorized", false);
        }
        return "Products/bookView";
    }

    @GetMapping("/books/cart/add/{bookId}")
    public String addToCart(@PathVariable Integer bookId, Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        responseUser.data.addBook(bookId);
        userService.update(responseUser.data);
        return "redirect:/books";
    }

    @GetMapping("/books/cart/delete/{bookId}")
    public String deleteFromCart(@PathVariable Integer bookId, Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        responseUser.data.deleteBook(bookId);
        userService.update(responseUser.data);
        return "redirect:/books";
    }

    @PostMapping("/books/create")
    public String createBook(@RequestParam Book book, Principal principal, Model model) {
        bookService.create(book);
        return "redirect:/admin";
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Principal principal, Model model) {
        bookService.delete(bookId);
        return "redirect:/admin";
    }
}
