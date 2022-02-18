package com.vn.VLXD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.VLXD.entities.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountName(String username);
    Boolean existsByAccountName(String username);
}
