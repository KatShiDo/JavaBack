package com.mirea.JavaBack.Controllers;

import com.mirea.JavaBack.Domain.Entities.Book;
import com.mirea.JavaBack.Domain.Entities.User;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Services.BookService;
import com.mirea.JavaBack.Services.UserService;
import freemarker.template.Configuration;
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

    private final BookService bookService;
    private final UserService userService;

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
            if (responseUser.data.getBooks().isEmpty()) {
                model.addAttribute("hasBooks", false);
            }
            else {
                model.addAttribute("hasBooks", true);
                model.addAttribute("userBooks", responseUser.data.getBooks());
            }
        }
        else {
            model.addAttribute("user", new User());
            model.addAttribute("userAuthorized", false);
            model.addAttribute("hasBooks", false);
        }
        return "Products/bookView";
    }

    @GetMapping("/books/cart/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        var responseBook = bookService.getById(bookId);
        if (responseBook.data.getCount() >= 1) {
            responseUser.data.addBook(bookId);
            userService.update(responseUser.data);
            responseBook.data.decreaseCount();
            bookService.update(responseBook.data);
        }
        return "redirect:/books";

    }

    @GetMapping("/books/cart/delete/{bookId}")
    public String deleteFromCart(@PathVariable Long bookId, Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        responseUser.data.deleteBook(bookId);
        userService.update(responseUser.data);
        var responseBook = bookService.getById(bookId);
        responseBook.data.increaseCount();
        bookService.update(responseBook.data);
        return "redirect:/books";
    }

    @GetMapping("/books/cart/increase/{bookId}")
    public String increase(@PathVariable Long bookId, Principal principal, Model model) {
        var responseBook = bookService.getById(bookId);
        if (responseBook.data.getCount() >= 1) {
            var responseUser = userService.getByPrincipal(principal);
            responseUser.data.increaseBook(bookId);
            userService.update(responseUser.data);
            responseBook.data.decreaseCount();
            bookService.update(responseBook.data);
        }
        return "redirect:/cart";
    }

    @GetMapping("/books/cart/decrease/{bookId}")
    public String decrease(@PathVariable Long bookId, Principal principal, Model model) {
        var responseUser = userService.getByPrincipal(principal);
        responseUser.data.decreaseBook(bookId);
        userService.update(responseUser.data);
        var responseBook = bookService.getById(bookId);
        responseBook.data.increaseCount();
        bookService.update(responseBook.data);
        return "redirect:/cart";
    }

    @PostMapping("/books/create")
    public String createBook(Book book, Principal principal, Model model) {
        bookService.create(book);
        return "redirect:/admin";
    }

    @GetMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId, Principal principal, Model model) {
        bookService.delete(bookId);
        return "redirect:/admin";
    }
}
