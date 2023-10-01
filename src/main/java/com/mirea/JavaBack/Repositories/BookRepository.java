package com.mirea.JavaBack.Repositories;

import com.mirea.JavaBack.Domain.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
