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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Book book) {
        bookService.create(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll(@RequestHeader("token") String token) {
        System.out.println("////////////////" + token);
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/cart/add/{bookId}")
    public void addToCard(@PathVariable Long bookId, @RequestHeader("token") String token) {
        bookService.addToCart(bookId, token);
    }
}
