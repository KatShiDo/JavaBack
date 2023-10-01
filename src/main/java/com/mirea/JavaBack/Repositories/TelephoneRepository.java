package com.mirea.JavaBack.Repositories;

import com.mirea.JavaBack.Domain.Entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
