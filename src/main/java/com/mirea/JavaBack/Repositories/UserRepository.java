package com.mirea.JavaBack.Repositories;

import com.mirea.JavaBack.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
