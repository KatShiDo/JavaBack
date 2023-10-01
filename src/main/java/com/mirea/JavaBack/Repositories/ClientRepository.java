package com.mirea.JavaBack.Repositories;

import com.mirea.JavaBack.Domain.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
