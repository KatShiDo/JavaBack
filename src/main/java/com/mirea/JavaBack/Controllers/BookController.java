package com.mirea.JavaBack.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mirea.JavaBack.Domain.Entities.Book;
import com.mirea.JavaBack.Services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping("/books")
    String getAll() {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(bookService.getAll());
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/books/{id}")
    String getById(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(bookService.getById(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/books")
    String create(@RequestBody Book book) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(bookService.create(book));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/books")
    String update(@RequestBody Book book) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(bookService.update(book));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/books/{id}")
    String delete(@PathVariable Long id) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(bookService.delete(id));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
