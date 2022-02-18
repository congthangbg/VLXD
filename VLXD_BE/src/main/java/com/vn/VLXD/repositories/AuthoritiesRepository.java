package com.vn.VLXD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
	Optional<Authorities> findByRoleIdAndAccountId(Long roleId,Long accountId);
	
	Optional<Authorities> findByAccountId(Long accountId);
}
