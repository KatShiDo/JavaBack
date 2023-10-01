package com.mirea.JavaBack.Services;

import com.mirea.JavaBack.Domain.Entities.Book;
import com.mirea.JavaBack.Domain.Enums.StatusCode;
import com.mirea.JavaBack.Domain.Response.BaseResponse;
import com.mirea.JavaBack.Repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BaseResponse<List<Book>> getAll() {
        try {
            var books = bookRepository.findAll();
            if (books.isEmpty()) {
                return new BaseResponse<>("Found 0 elements", books, StatusCode.Ok);
            }
            return new BaseResponse<>(null, books, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Book> getById(Long id) {
        try {
            var book = bookRepository.findById(id).orElse(null);
            if (book == null) {
                return new BaseResponse<>("Book not found", null, StatusCode.NotFound);
            }
            return new BaseResponse<>(null, book, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Book> create(Book book) {
        try {
            book = bookRepository.save(book);
            return new BaseResponse<>(null, book, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Book> update(Book book) {
        try {
            var bookFromDb = bookRepository.findById(book.getId()).orElse(null);
            if (bookFromDb == null) {
                return new BaseResponse<>("Book not found", null, StatusCode.NotFound);
            }
            book = bookRepository.save(book);
            return new BaseResponse<>(null, book, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), null, StatusCode.InternalServerError);
        }
    }

    public BaseResponse<Boolean> delete(Long id) {
        try {
            bookRepository.deleteById(id);
            return new BaseResponse<>(null, true, StatusCode.Ok);
        }
        catch (Exception e) {
            return new BaseResponse<>(e.getMessage(), false, StatusCode.InternalServerError);
        }
    }
}
