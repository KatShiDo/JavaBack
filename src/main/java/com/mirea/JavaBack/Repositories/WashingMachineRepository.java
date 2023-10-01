package com.mirea.JavaBack.Repositories;

import com.mirea.JavaBack.Domain.Entities.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {
}
