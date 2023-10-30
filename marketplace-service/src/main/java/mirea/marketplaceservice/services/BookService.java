package mirea.marketplaceservice.services;

import lombok.RequiredArgsConstructor;
import mirea.marketplaceservice.client.UserInfoClient;
import mirea.marketplaceservice.domain.dto.UserInfo;
import mirea.marketplaceservice.domain.entities.Book;
import mirea.marketplaceservice.repositories.BookRepository;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserInfoClient userInfoClient;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void create(Book book) {
        bookRepository.save(book);
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

    public void addToCart(Long bookId, String token) {
        var book = bookRepository.findById(bookId).orElse(null);
        UserInfo user = userInfoClient.getUser("123", "Bearer " + token);
//        RestTemplate restTemplate = new RestTemplate();
//        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer " + token));
//        restTemplate.setInterceptors(interceptors);
//        UserInfo response = restTemplate.getForObject("http://localhost:8070/auth/get?secret=secret", UserInfo.class);
        user.addBook(bookId);
        //userInfoClient.saveUser(user, "secret", "Bearer " + token);
    }
}
