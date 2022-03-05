package com.vn.VLXD.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vn.VLXD.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
	@Query(value = "select s from Supplier s where :keySearch is null or s.name like %:keySearch%")
	Page<Supplier> findAllSearch(String keySearch,Pageable pageable);
}
