package com.mirea.JavaBack.Domain.Entities;


import com.mirea.JavaBack.Domain.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String login;

    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @ElementCollection
    private Map<String, Integer> books = new HashMap<>();

    public void addBook(Long bookId) {
        books.put(bookId.toString(), 1);
    }

    public void increaseBook(Long bookId) {
        books.put(bookId.toString(), books.get(bookId.toString()) + 1);
    }

    public void decreaseBook(Long bookId) {
        books.put(bookId.toString(), books.get(bookId.toString()) - 1);
    }

    public void deleteBook(Long bookId) {
        books.remove(bookId.toString());
    }

    public void emptyBooks() {
        books = new HashMap<>();
    }

    @ElementCollection
    private Map<Integer, Integer> telephones = new HashMap<>();

    @ElementCollection
    private Map<Integer, Integer> washingMachines = new HashMap<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
