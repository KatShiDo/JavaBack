package mirea.marketplaceservice.controllers;

import lombok.RequiredArgsConstructor;
import mirea.marketplaceservice.domain.entities.Book;
import mirea.marketplaceservice.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(@RequestHeader("token") String token) {
        System.out.println("////////////////" + token);
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/cart/add/{bookId}")
    public String addToCard(@PathVariable Long bookId, @RequestHeader("token") String token) {
        return bookService.addToCart(bookId, token);
    }

    @GetMapping("/upgrade")
    public String upgradeRole(@RequestHeader("token") String token) {
        return bookService.upgradeRole(token);
    }

    @PostMapping("/add")
    public String addBook(@RequestHeader("token") String token, @RequestBody Book book) {
        return bookService.create(book, token);
    }

    @PostMapping("/delete/{bookId}")
    public String deleteBook(@RequestHeader("token") String token, @PathVariable Long bookId) {
        return bookService.delete(bookId, token);
    }
}
