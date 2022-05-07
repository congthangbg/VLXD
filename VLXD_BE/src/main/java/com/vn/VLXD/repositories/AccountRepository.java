package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vn.VLXD.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountName(String username);
    Boolean existsByAccountName(String username);
    
    @Query(value = "select a from Account a where :keySearch is null or  a.accountName like %:keySearch% and a.status = 1")
    Page<Account> findAlBySearch(String keySearch,Pageable pageable);
}
