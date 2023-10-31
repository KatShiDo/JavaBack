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
    private RestTemplate restTemplate = new RestTemplate();

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public String create(Book book, String token) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer " + token));
        restTemplate.setInterceptors(interceptors);
        UserInfo response = restTemplate.getForObject("http://localhost:8080/auth/get?secret=123", UserInfo.class);
        if (response.getRole().equals("ROLE_SELLER") || response.getRole().equals("ROLE_ADMIN")) {
            bookRepository.save(book);
            return "success";
        }
        else {
            return "no access";
        }
    }

    public Book update(Book book) {
        var bookFromDb = bookRepository.findById(book.getId()).orElse(null);
        if (bookFromDb == null) {
            return null;
        }
        return bookRepository.save(book);
    }

    public String delete(Long id, String token) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer " + token));
        restTemplate.setInterceptors(interceptors);
        UserInfo response = restTemplate.getForObject("http://localhost:8080/auth/get?secret=123", UserInfo.class);
        if (response.getRole().equals("ROLE_SELLER") || response.getRole().equals("ROLE_ADMIN")) {
            bookRepository.deleteById(id);
            return "deleted";
        }
        else {
            return "no access";
        }
    }

    public String addToCart(Long bookId, String token) {
        //UserInfo user = userInfoClient.getUser("123", "Bearer " + token);
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer " + token));
        restTemplate.setInterceptors(interceptors);
        UserInfo response = restTemplate.getForObject("http://localhost:8080/auth/get?secret=123", UserInfo.class);
        response.addBook(bookId);
        return restTemplate.postForObject("http://localhost:8080/auth/save?secret=123", response, String.class);
        //userInfoClient.saveUser(user, "secret", "Bearer " + token);
    }

    public String upgradeRole(String token) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Authorization", "Bearer " + token));
        restTemplate.setInterceptors(interceptors);
        UserInfo response = restTemplate.getForObject("http://localhost:8080/auth/get?secret=123", UserInfo.class);
        response.setRole("ROLE_SELLER");
        return restTemplate.postForObject("http://localhost:8080/auth/save?secret=123", response, String.class);
    }
}
