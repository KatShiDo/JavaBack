package mirea.marketplaceservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

    private String username;
    private String email;
    private String role;
    private List<Long> books;

    public void addBook(Long bookId) {
        books.add(bookId);
    }

    public void removeBook(Long bookId) {
        books.remove(bookId);
    }
}
