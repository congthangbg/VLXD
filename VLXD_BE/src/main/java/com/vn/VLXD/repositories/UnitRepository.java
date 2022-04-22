package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
	
	@Query(value = "select s from Unit s where s.status != 0 and ( :keySearch is null or s.unitName like %:keySearch%)")
	Page<Unit> findAllSearch(String keySearch,Pageable pageable);
}
