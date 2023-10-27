package mirea.marketplaceservice.services;

import lombok.RequiredArgsConstructor;
import mirea.marketplaceservice.domain.entities.Book;
import mirea.marketplaceservice.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        var bookFromDb = bookRepository.findById(book.getId()).orElse(null);
        if (bookFromDb == null) {
            return null;
        }
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
